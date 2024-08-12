package com.example.taskmaster.model.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.taskmaster.model.Priority
import com.example.taskmaster.model.Task
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate

@Dao
interface TaskDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addTask(task: Task): Long

    @Query("SELECT * FROM tasks")
    fun getALLTasks(): Flow<List<Task>>

    @Query("SELECT * FROM tasks WHERE id = :taskId")
    fun getTaskById(taskId: Long): Flow<Task?>

    @Query("UPDATE tasks SET isCompleted = :isCompleted WHERE id = :taskId")
    suspend fun updateCompleted(
        taskId: Long,
        isCompleted: Boolean,
    )

    @Query(
        "UPDATE tasks " +
            "SET title = :title," +
            "dueDate = :dueDate," +
            "priority = :priority," +
            "isCompleted = :isCompleted " +
            " WHERE id = :taskId",
    )
    suspend fun updateTask(
        taskId: Long,
        title: String,
        dueDate: LocalDate,
        priority: Priority,
        isCompleted: Boolean,
    )
}
