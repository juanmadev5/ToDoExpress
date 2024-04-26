package com.jmdev.app.todo.data.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [TaskEntity::class], version = 1)
abstract class Database : RoomDatabase() {
    abstract val taskDao: TaskDao
}