package com.example.taskmaster.screen.edit

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.taskmaster.model.Priority
import com.example.taskmaster.model.Task
import com.example.taskmaster.model.repository.TaskRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class EditViewModel
    @Inject
    constructor(
        private val taskRepository: TaskRepository,
    ) : ViewModel() {
        val task =
            mutableStateOf(
                Task(
                    title = "",
                    dueDate = null,
                    priority = Priority.None,
                    isCompleted = false,
                ),
            )

        fun onTitleChange(newValue: String) {
            task.value = task.value.copy(title = newValue)
        }

        fun onDueDateChange(newValue: LocalDate?) {
            task.value = task.value.copy(dueDate = newValue)
        }

        fun onCompletedChange(newValue: Boolean) {
            task.value = task.value.copy(isCompleted = newValue)
        }

        fun updateTask() {
            viewModelScope.launch {
                taskRepository.updateTask(task.value)
                Log.d("EditViewModel", "Task updated: $task")
            }
        }

        fun getTaskById(taskId: Long) {
            viewModelScope.launch {
                taskRepository.getTaskById(taskId).collect { taskDetail ->
                    if (taskDetail != null) {
                        task.value = taskDetail
                        Log.d("EditViewModel", "Task loaded: $taskDetail")
                    }
                }
            }
        }
    }
