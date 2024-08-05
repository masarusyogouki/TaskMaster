package com.example.taskmaster.model.repository

import com.example.taskmaster.model.Priority
import com.example.taskmaster.model.Task
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate

interface TaskRepository {
    suspend fun addTask(task: Task): Long

    fun getTasks(): Flow<List<Task>>

    suspend fun updateTaskTitle(
        taskId: Long,
        title: String,
    )

    suspend fun updateTaskDueDate(
        taskId: Long,
        dueDate: LocalDate,
    )

    suspend fun updateTaskPriority(
        taskId: Long,
        priority: Priority,
    )

    suspend fun updateTaskCompleted(
        taskId: Long,
        isCompleted: Boolean,
    )
}
