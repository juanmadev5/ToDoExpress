package com.jmdev.app.todo.data

import android.content.Context
import androidx.room.Room
import com.jmdev.app.todo.App
import com.jmdev.app.todo.data.database.Database
import com.jmdev.app.todo.data.database.TaskEntity
import dagger.hilt.android.qualifiers.ApplicationContext
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import javax.inject.Inject

class Repository @Inject constructor(
    @ApplicationContext private val context: Context
) {
    private fun database() = Room.databaseBuilder(
        context,
        Database::class.java,
        App.DB_NAME
    ).build()

    suspend fun getTasks(): List<TaskEntity> {
        return database().taskDao.getTasks()
    }

    suspend fun createTask(name: String): TaskEntity {
        val date = Date()
        val currentDate = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(date)
        val currentTime = SimpleDateFormat("HH:mm", Locale.getDefault()).format(date)
        val model = TaskEntity(
            taskName = name,
            taskState = false,
            createdAt = "$currentDate $currentTime",
            completedAt = null
        )
        database().taskDao.saveTask(
            model
        )
        return model
    }

    suspend fun setCompleted(taskId: Int) {
        database().taskDao.updateTaskState(taskId)
    }


    fun getCompletedTasks(): List<TaskEntity> {
        return database().taskDao.getCompletedTasks()
    }

    suspend fun deleteTask(id: Int) {
        database().taskDao.deleteTask(id)
    }

    suspend fun deleteCompletedTasks() {
        database().taskDao.deleteCompletedTasks()
    }
}