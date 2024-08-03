package com.example.taskmaster.screen.home

import com.example.taskmaster.model.Task

data class HomeUiState(
    val title: String = "",
    val tasks: List<Task> = emptyList(),
)
