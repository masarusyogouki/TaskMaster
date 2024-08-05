package com.example.taskmaster.model.data

import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.taskmaster.model.Task
import com.example.taskmaster.model.module.Converters

@Database(
    entities = [Task::class],
    version = 2,
    exportSchema = false,
    autoMigrations = [
        AutoMigration(from = 1, to = 2),
    ],
)
@TypeConverters(Converters::class)
abstract class TaskDatabase : RoomDatabase() {
    abstract fun taskDao(): TaskDao
}
