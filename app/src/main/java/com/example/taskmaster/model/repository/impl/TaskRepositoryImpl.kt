package com.example.taskmaster.model.repository.impl

import com.example.taskmaster.model.Task
import com.example.taskmaster.model.data.TaskDao
import com.example.taskmaster.model.repository.TaskRepository
import javax.inject.Inject

class TaskRepositoryImpl
    @Inject
    constructor(
        private val taskDao: TaskDao,
    ) : TaskRepository {
        override suspend fun addTask(task: Task): Long = taskDao.addTask(task)
    }
