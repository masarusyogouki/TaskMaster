package com.example.taskmaster.model.module

import android.content.Context
import androidx.room.Room
import com.example.taskmaster.model.data.TaskDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object TaskModule {
    @Singleton
    @Provides
    fun provideDatabase(
        @ApplicationContext context: Context,
    ) = Room
        .databaseBuilder(
            context,
            TaskDatabase::class.java,
            "task_database",
        ).build()

    @Singleton
    @Provides
    fun provideTaskDao(db: TaskDatabase) = db.taskDao()
}
