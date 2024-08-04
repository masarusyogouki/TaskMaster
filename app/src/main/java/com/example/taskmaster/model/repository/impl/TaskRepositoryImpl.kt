package com.example.taskmaster.model.repository.impl

import com.example.taskmaster.model.Priority
import com.example.taskmaster.model.Task
import com.example.taskmaster.model.data.TaskDao
import com.example.taskmaster.model.repository.TaskRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class TaskRepositoryImpl
    @Inject
    constructor(
        private val taskDao: TaskDao,
    ) : TaskRepository {
        override suspend fun addTask(task: Task): Long = taskDao.addTask(task)

        override fun getTasks(): Flow<List<Task>> = taskDao.getALLTasks()

        override suspend fun updateTaskCompleted(
            taskId: Long,
            isCompleted: Boolean,
        ) = taskDao.updateTaskCompleted(taskId, isCompleted)

        override suspend fun updateTaskPriority(
            taskId: Long,
            priority: Priority,
        ) = taskDao.updateTaskPriority(taskId, priority)
    }
