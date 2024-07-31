package com.example.taskmaster.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tasks")
data class Task(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val title: String,
    val dueDate: String? = null,
    val priority: Priority,
    val isCompleted: Boolean = false,
)
