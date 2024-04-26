package com.jmdev.app.todo.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface TaskDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveTask(taskEntity: TaskEntity)

    @Query("SELECT * FROM TaskEntity WHERE id =:taskId")
    suspend fun getTask(taskId: Int): TaskEntity

    @Query("SELECT * FROM TaskEntity WHERE task_state = 0")
    suspend fun getTasks(): List<TaskEntity>

    @Query("UPDATE TaskEntity SET task_state = 1 WHERE id =:taskId")
    suspend fun updateTaskState(taskId: Int)

    @Query("SELECT * FROM TaskEntity WHERE task_state = 1")
    fun getCompletedTasks(): List<TaskEntity>

    @Query("DELETE FROM TaskEntity WHERE task_state = 1")
    suspend fun deleteCompletedTasks()

    @Query("DELETE FROM TaskEntity WHERE id =:id")
    suspend fun deleteTask(id: Int)
}