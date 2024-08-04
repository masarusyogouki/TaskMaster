package com.example.taskmaster.model.repository

import com.example.taskmaster.model.Priority
import com.example.taskmaster.model.Task
import kotlinx.coroutines.flow.Flow

interface TaskRepository {
    suspend fun addTask(task: Task): Long

    fun getTasks(): Flow<List<Task>>

    suspend fun updateTaskCompleted(
        taskId: Long,
        isCompleted: Boolean,
    )

    suspend fun updateTaskPriority(
        taskId: Long,
        priority: Priority,
    )
}
