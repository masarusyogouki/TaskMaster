package com.example.taskmaster.model.repository

import com.example.taskmaster.model.Priority
import com.example.taskmaster.model.Task
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate

interface TaskRepository {
    suspend fun addTask(task: Task): Long

    fun getTasks(): Flow<List<Task>>

    fun getTaskById(taskId: Long): Flow<Task?>

    suspend fun updateCompleted(
        taskId: Long,
        isCompleted: Boolean,
    )

    suspend fun updateTask(
        taskId: Long,
        title: String,
        dueDate: LocalDate,
        priority: Priority,
        isCompleted: Boolean,
    )
}
