package com.example.taskmaster.screen.edit

import androidx.lifecycle.ViewModel
import com.example.taskmaster.model.repository.TaskRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class EditViewModel
    @Inject
    constructor(
        private val taskRepository: TaskRepository,
    ) : ViewModel() {
        // Implement the logic for editing tasks here
    }
