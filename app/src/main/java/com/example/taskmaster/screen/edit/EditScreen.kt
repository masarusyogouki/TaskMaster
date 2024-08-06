package com.example.taskmaster.screen.edit

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.taskmaster.model.Task

@Composable
fun EditScreen(
    taskId: Long,
    viewModel: EditViewModel = hiltViewModel(),
) {
    LaunchedEffect(taskId) {
        viewModel.loadTask(taskId)
    }

    val task by viewModel.task.collectAsState()

    EditScreenContent(
        task = task,
    )
}

@Composable
fun EditScreenContent(
    task: Task?,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier.padding(16.dp)) {
        task?.let {
            Text(text = "Title: ${it.title}")
            Text(text = "ID: ${it.id}")
            Text(text = "Due Date: ${it.dueDate}")
            Text(text = "Priority: ${it.priority}")
            Text(text = "Completed: ${it.isCompleted}")
        } ?: run {
            Text(text = "Task not found")
        }
    }
}
