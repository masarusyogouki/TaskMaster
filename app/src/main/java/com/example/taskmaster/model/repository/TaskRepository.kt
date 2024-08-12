package com.example.taskmaster.model.repository

import com.example.taskmaster.model.Task
import kotlinx.coroutines.flow.Flow

interface TaskRepository {
    suspend fun addTask(task: Task): Long

    fun getTasks(): Flow<List<Task>>

    fun getTaskById(taskId: Long): Flow<Task?>

    suspend fun updateCompleted(
        taskId: Long,
        isCompleted: Boolean,
    )

    suspend fun updateTask(task: Task)
}
