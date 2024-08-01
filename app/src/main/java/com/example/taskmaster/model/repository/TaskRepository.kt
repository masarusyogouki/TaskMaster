package com.example.taskmaster.model.repository

import com.example.taskmaster.model.Task

interface TaskRepository {
    suspend fun addTask(task: Task): Long

    suspend fun getAllTasks(): List<Task>
}
