package com.example.taskmaster.model.repository.impl

import com.example.taskmaster.model.Priority
import com.example.taskmaster.model.Task
import com.example.taskmaster.model.data.TaskDao
import com.example.taskmaster.model.repository.TaskRepository
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate
import javax.inject.Inject

class TaskRepositoryImpl
    @Inject
    constructor(
        private val taskDao: TaskDao,
    ) : TaskRepository {
        override suspend fun addTask(task: Task): Long = taskDao.addTask(task)

        override fun getTasks(): Flow<List<Task>> = taskDao.getALLTasks()

        override fun getTaskById(taskId: Long): Flow<Task?> = taskDao.getTaskById(taskId)

        override suspend fun updateTaskTitle(
            taskId: Long,
            title: String,
        ) = taskDao.updateTaskTitle(taskId, title)

        override suspend fun updateTaskDueDate(
            taskId: Long,
            dueDate: LocalDate,
        ) = taskDao.updateTaskDueDate(taskId, dueDate)

        override suspend fun updateTaskPriority(
            taskId: Long,
            priority: Priority,
        ) = taskDao.updateTaskPriority(taskId, priority)

        override suspend fun updateTaskCompleted(
            taskId: Long,
            isCompleted: Boolean,
        ) = taskDao.updateTaskCompleted(taskId, isCompleted)
    }
