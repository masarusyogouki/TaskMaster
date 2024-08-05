package com.example.taskmaster.screen.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.taskmaster.TodoNavigationActions
import com.example.taskmaster.common.composable.NewTaskField
import com.example.taskmaster.common.composable.TaskCard
import com.example.taskmaster.model.Priority

@Composable
fun HomeScreen(
    navController: NavHostController,
    viewModel: HomeViewModel = hiltViewModel(),
) {
    val uiState = viewModel.uiState.value
    val navActions = remember(navController) { TodoNavigationActions(navController) }

    HomeScreenContent(
        uiState = uiState,
        onAddClick = viewModel::addTask,
        onTitleChange = viewModel::onTitleChange,
        onTaskCompletedChange = viewModel::onUpdateTaskCompleted,
        onTaskPriorityChange = viewModel::onUpdateTaskPriority,
        onNavEditClick = { navActions.navigateToEditScreen() },
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreenContent(
    uiState: HomeUiState,
    onTitleChange: (String) -> Unit,
    onAddClick: (String) -> Unit,
    onTaskCompletedChange: (Long, Boolean) -> Unit,
    onTaskPriorityChange: (Long, Priority) -> Unit,
    onNavEditClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Home Screen") },
            )
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
                            isCompleted = task.isCompleted,
                            title = task.title,
                            priority = task.priority,
                            onCompletedChange = { isCompleted ->
                                onTaskCompletedChange(task.id, isCompleted)
                            },
                            onPriorityChange = { priority ->
                                onTaskPriorityChange(task.id, priority)
                            },
                            onNavEditClick = {
                                onNavEditClick()
                            },
                        )
                    }
                }
            }
            NewTaskField(
                value = uiState.title,
                onNewValue = onTitleChange,
                onClick = { onAddClick(uiState.title) },
                modifier = Modifier.align(Alignment.BottomCenter),
            )
        }
    }
}
