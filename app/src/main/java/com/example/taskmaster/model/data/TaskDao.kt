package com.example.taskmaster.model.data

import androidx.room.Dao
import androidx.room.Delete
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

    @Query("UPDATE tasks SET title = :title WHERE id = :taskId")
    suspend fun updateTitle(
        taskId: Long,
        title: String,
    )

    @Query("UPDATE tasks SET dueDate = :dueDate WHERE id = :taskId")
    suspend fun updateDueDate(
        taskId: Long,
        dueDate: LocalDate?,
    )

    @Query("UPDATE tasks SET priority = :priority WHERE id = :taskId")
    suspend fun updatePriority(
        taskId: Long,
        priority: Priority,
    )

    @Query("UPDATE tasks SET isCompleted = :isCompleted WHERE id = :taskId")
    suspend fun updateCompleted(
        taskId: Long,
        isCompleted: Boolean,
    )

    @Delete
    suspend fun deleteTask(task: Task)
}
