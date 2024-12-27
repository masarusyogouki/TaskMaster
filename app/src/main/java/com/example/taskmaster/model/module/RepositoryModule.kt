package com.example.taskmaster.model.module

import com.example.taskmaster.model.repository.AuthRepository
import com.example.taskmaster.model.repository.TaskRepository
import com.example.taskmaster.model.repository.impl.AuthRepositoryImpl
import com.example.taskmaster.model.repository.impl.TaskRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
abstract class RepositoryModule {
    @Binds
    abstract fun bindTaskRepository(impl: TaskRepositoryImpl): TaskRepository
    @Binds
    abstract fun bindAuthRepository(impl: AuthRepositoryImpl): AuthRepository
}
