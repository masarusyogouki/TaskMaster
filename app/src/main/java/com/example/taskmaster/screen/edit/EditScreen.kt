package com.example.taskmaster.screen.edit

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun EditScreen(
    taskId: Long,
    viewModel: EditViewModel = hiltViewModel(),
) {
    EditScreenContent(
        taskId = taskId,
    )
}

@Composable
fun EditScreenContent(
    taskId: Long,
    modifier: Modifier = Modifier,
) {
    Text("ID: $taskId")
}
