package com.example.taskmaster.screen.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.taskmaster.common.composable.NewTaskField
import com.example.taskmaster.common.composable.TaskCard
import com.example.taskmaster.model.Task

@Composable
fun HomeScreen(
    onNavEditClick: (Task) -> Unit,
    viewModel: HomeViewModel = hiltViewModel(),
) {
    val uiState = viewModel.uiState.value

    HomeScreenContent(
        uiState = uiState,
        onAddClick = viewModel::addTask,
        onTitleChange = viewModel::onTitleChange,
        onUpdateCompleted = viewModel::onUpdateCompleted,
        onDeleteTask = viewModel::onDeleteTask,
        onNavEditClick = onNavEditClick,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreenContent(
    uiState: HomeUiState,
    onTitleChange: (String) -> Unit,
    onAddClick: (String) -> Unit,
    onUpdateCompleted: (Long, Boolean) -> Unit,
    onDeleteTask: (Task) -> Unit,
    onNavEditClick: (Task) -> Unit,
    modifier: Modifier = Modifier,
) {
    Scaffold(
        topBar = {
            Column {
                TopAppBar(
                    title = { Text("Home Screen") },
                )
                HorizontalDivider(thickness = 1.dp, color = Color.Black)
            }
        },
        bottomBar = {
            Column {
                HorizontalDivider(thickness = 1.dp, color = Color.Black)
                NewTaskField(
                    value = uiState.title,
                    onNewValue = onTitleChange,
                    onClick = { onAddClick(uiState.title) },
                )
            }
        },
    ) { innerPadding ->
        Box(
            modifier =
                modifier
                    .fillMaxSize()
                    .padding(innerPadding),
        ) {
            Column(modifier = Modifier.fillMaxSize()) {
                LazyColumn(
                    modifier =
                        Modifier
                            .weight(1f)
                            .fillMaxWidth(),
                ) {
                    items(uiState.tasks) { task ->
                        TaskCard(
                            task = task,
                            onCompletedChange = { isCompleted ->
                                onUpdateCompleted(task.id, isCompleted)
                            },
                            onDeleteTask = {
                                onDeleteTask(task)
                            },
                            onNavEditClick = onNavEditClick,
                        )
                    }
                }
            }
        }
    }
}
