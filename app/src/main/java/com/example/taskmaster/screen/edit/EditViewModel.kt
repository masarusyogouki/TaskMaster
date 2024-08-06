package com.example.taskmaster.screen.edit

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.taskmaster.model.Task
import com.example.taskmaster.model.repository.TaskRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EditViewModel
    @Inject
    constructor(
        private val taskRepository: TaskRepository,
    ) : ViewModel() {
        private val _task = MutableStateFlow<Task?>(null)
        val task: StateFlow<Task?> = _task.asStateFlow()

        fun loadTask(taskId: Long) {
            viewModelScope.launch {
                taskRepository.getTaskById(taskId).collect { task ->
                    _task.value = task
                }
            }
        }
    }
