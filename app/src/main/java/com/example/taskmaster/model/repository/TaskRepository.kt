package com.example.taskmaster.model.repository

import com.example.taskmaster.model.Task
import kotlinx.coroutines.flow.Flow

interface TaskRepository {
    val tasks: Flow<List<Task>>

    suspend fun addTask(task: Task): Long
}
