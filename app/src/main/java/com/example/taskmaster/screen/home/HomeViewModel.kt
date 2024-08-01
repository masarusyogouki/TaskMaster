package com.example.taskmaster.screen.home

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.taskmaster.model.Priority
import com.example.taskmaster.model.Task
import com.example.taskmaster.model.repository.TaskRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel
    @Inject
    constructor(
        private val taskRepository: TaskRepository,
    ) : ViewModel() {
        var uiState = mutableStateOf(HomeUiState())
            private set

        val tasks: LiveData<List<Task>> = taskRepository.tasks.asLiveData()

        fun onTitleChange(newValue: String) {
            uiState.value = uiState.value.copy(title = newValue)
        }

        fun addTask(title: String) {
            viewModelScope.launch {
                val newTask =
                    Task(
                        title = title,
                        dueDate = null,
                        priority = Priority.None,
                        isCompleted = false,
                    )
                taskRepository.addTask(newTask)
                onTitleChange("")
                Log.d("HomeViewModel", "Task added: $newTask")
            }
        }
    }
