package com.example.taskmaster.screen.edit

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.taskmaster.common.composable.EditCard
import com.example.taskmaster.model.Task

@Composable
fun EditScreen(
    taskId: Long,
    viewModel: EditViewModel = hiltViewModel(),
) {
    LaunchedEffect(taskId) {
        viewModel.getTaskById(taskId)
    }

    val task = viewModel.task.value

    EditScreenContent(
        task = task,
        onTitleChange = viewModel::onTitleChange,
        onCompletedChange = viewModel::onCompletedChange,
    )
}

@Composable
fun EditScreenContent(
    task: Task?,
    onTitleChange: (String) -> Unit,
    onCompletedChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
) {
    task?.let { taskDetail ->
        EditCard(
            title = taskDetail.title,
            newValue = onTitleChange,
            isCompleted = taskDetail.isCompleted,
            onCompletedChange = onCompletedChange,
            modifier = modifier,
        )
    }
}
