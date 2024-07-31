package com.example.taskmaster.model.module

import com.example.taskmaster.model.repository.TaskRepository
import com.example.taskmaster.model.repository.impl.TaskRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
abstract class TaskModule {
    @Binds
    abstract fun bindTaskRepository(impl: TaskRepositoryImpl): TaskRepository
}
