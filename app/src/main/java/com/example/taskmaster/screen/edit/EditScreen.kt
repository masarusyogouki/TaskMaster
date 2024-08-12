package com.example.taskmaster.screen.edit

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.taskmaster.common.composable.DatePickerField
import com.example.taskmaster.common.composable.EditTitleCard
import com.example.taskmaster.model.Task
import java.time.LocalDate

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
        onDueDateChange = viewModel::onDueDateChange,
        onUpdateTask = viewModel::updateTask,
    )
}

@Composable
fun EditScreenContent(
    task: Task?,
    onTitleChange: (String) -> Unit,
    onCompletedChange: (Boolean) -> Unit,
    onDueDateChange: (LocalDate?) -> Unit,
    onUpdateTask: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column {
        task?.let { taskDetail ->
            EditTitleCard(
                title = taskDetail.title,
                newValue = onTitleChange,
                isCompleted = taskDetail.isCompleted,
                onCompletedChange = onCompletedChange,
                modifier = modifier,
            )
        }
        DatePickerField(
            onDueDateChange = onDueDateChange,
        )

        Text(text = "${task?.id}")
        Text(text = "${task?.title}")
        Text(text = "${task?.isCompleted}")
        Text(text = "${task?.dueDate}")
        Text(text = "${task?.priority}")

        Button(onClick = onUpdateTask) {
            Text(text = "更新")
        }
    }
}
