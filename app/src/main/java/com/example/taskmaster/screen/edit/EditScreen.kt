package com.example.taskmaster.screen.edit

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.taskmaster.common.composable.EditDialogCard
import com.example.taskmaster.common.composable.EditTitleCard
import com.example.taskmaster.model.Priority
import com.example.taskmaster.model.Task
import java.time.LocalDate

@Composable
fun EditScreen(
    taskId: Long,
    navController: NavController,
    viewModel: EditViewModel = hiltViewModel(),
) {
    LaunchedEffect(taskId) {
        viewModel.getTaskById(taskId)
    }

    val task = viewModel.task.value

    EditScreenContent(
        task = task,
        navController = navController,
        onTitleChange = viewModel::onTitleChange,
        onCompletedChange = viewModel::onCompletedChange,
        onDueDateChange = viewModel::onDueDateChange,
        onPriorityChange = viewModel::onPriorityChange,
        onUpdateTask = viewModel::updateTask,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditScreenContent(
    task: Task?,
    navController: NavController,
    onTitleChange: (String) -> Unit,
    onCompletedChange: (Boolean) -> Unit,
    onDueDateChange: (LocalDate?) -> Unit,
    onPriorityChange: (Priority) -> Unit,
    onUpdateTask: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Scaffold(
        topBar = {
            TopAppBar(
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.KeyboardArrowLeft,
                            contentDescription = "戻る",
                        )
                    }
                },
                title = { Text("Edit Screen") },
            )
        },
    ) { innerPadding ->
        Column(modifier = Modifier.padding(innerPadding)) {
            task?.let { taskDetail ->
                EditTitleCard(
                    title = taskDetail.title,
                    newValue = onTitleChange,
                    isCompleted = taskDetail.isCompleted,
                    onCompletedChange = onCompletedChange,
                    modifier = Modifier.padding(8.dp),
                )

                EditDialogCard(
                    task = taskDetail,
                    onDueDateChange = onDueDateChange,
                    onPriorityChange = onPriorityChange,
                    modifier = Modifier.padding(8.dp),
                )
            }

            Button(onClick = onUpdateTask) {
                Text(text = "更新")
            }
        }
    }
}
