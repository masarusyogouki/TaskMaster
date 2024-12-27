package com.example.taskmaster.screen.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.taskmaster.common.composable.NewTaskField
import com.example.taskmaster.common.composable.TaskCard
import com.example.taskmaster.model.Priority
import com.example.taskmaster.model.Task
import com.example.taskmaster.model.User

@Composable
fun HomeScreen(
    currentUser: User,
    onNavEditClick: (Task) -> Unit,
    onNavAuthClick: (String) -> Unit,
    viewModel: HomeViewModel = hiltViewModel(),
) {
    val uiState = viewModel.uiState.value

    HomeScreenContent(
        uiState = uiState,
        currentUser = currentUser,
        onAddClick = viewModel::addTask,
        onTitleChange = viewModel::onTitleChange,
        onUpdateCompleted = viewModel::onUpdateCompleted,
        onDeleteTask = viewModel::onDeleteTask,
        onNavEditClick = onNavEditClick,
        onNavAuthClick = onNavAuthClick,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreenContent(
    uiState: HomeUiState,
    currentUser: User,
    onTitleChange: (String) -> Unit,
    onAddClick: (String) -> Unit,
    onUpdateCompleted: (Long, Boolean) -> Unit,
    onDeleteTask: (Task) -> Unit,
    onNavEditClick: (Task) -> Unit,
    onNavAuthClick: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                colors =
                    TopAppBarDefaults.centerAlignedTopAppBarColors(
                        containerColor = MaterialTheme.colorScheme.primaryContainer,
                        titleContentColor = MaterialTheme.colorScheme.primary,
                    ),
                title = {
                    Text(
                        "ホーム",
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                    )
                },
                actions = {
                    IconButton(onClick = {
                        if (currentUser.uid.isNotEmpty()) {
                            onNavAuthClick("profile")
                        } else {
                            onNavAuthClick("auth")
                        }
                    }) {
                        Icon(
                            imageVector = Icons.Default.AccountCircle,
                            contentDescription = "makeAccount",
                        )
                    }
                },
            )
        },
        bottomBar = {
            Column {
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

@Preview(showBackground = true)
@Composable
private fun HomeScreenPreview() {
    val tasks =
        listOf(
            Task(
                id = 1,
                title = "Sample Task",
                dueDate = null,
                priority = Priority.HIGH,
                isCompleted = false,
            ),
            Task(
                id = 2,
                title = "Sample Task2",
                dueDate = null,
                priority = Priority.HIGH,
                isCompleted = false,
            ),
        )

    HomeScreenContent(
        uiState = HomeUiState(tasks = tasks),
        currentUser = User(),
        onAddClick = {},
        onTitleChange = {},
        onUpdateCompleted = { _, _ -> },
        onDeleteTask = {},
        onNavEditClick = {},
        onNavAuthClick = {},
    )
}
