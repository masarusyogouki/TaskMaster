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

        override suspend fun updateCompleted(
            taskId: Long,
            isCompleted: Boolean,
        ) {
            taskDao.updateCompleted(taskId, isCompleted)
        }

        override suspend fun updateTask(
            taskId: Long,
            title: String,
            dueDate: LocalDate,
            priority: Priority,
            isCompleted: Boolean,
        ) = taskDao.updateTask(taskId, title, dueDate, priority, isCompleted)
    }
