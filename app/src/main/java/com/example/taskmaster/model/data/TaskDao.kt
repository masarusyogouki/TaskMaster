package com.example.taskmaster.model.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.taskmaster.model.Priority
import com.example.taskmaster.model.Task
import kotlinx.coroutines.flow.Flow

@Dao
interface TaskDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addTask(task: Task): Long

    @Query("SELECT * FROM tasks")
    fun getALLTasks(): Flow<List<Task>>

    @Query("UPDATE tasks SET title = :title WHERE id = :taskId")
    suspend fun updateTaskTitle(
        taskId: Long,
        title: String,
    )

    @Query("UPDATE tasks SET priority = :priority WHERE id = :taskId")
    suspend fun updateTaskPriority(
        taskId: Long,
        priority: Priority,
    )

    @Query("UPDATE tasks SET isCompleted = :isCompleted WHERE id = :taskId")
    suspend fun updateTaskCompleted(
        taskId: Long,
        isCompleted: Boolean,
    )
}
