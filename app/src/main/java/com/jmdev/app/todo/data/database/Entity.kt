package com.jmdev.app.todo.data.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.jmdev.app.todo.model.TaskModel

@Entity
data class TaskEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo("task_name") val taskName: String,
    @ColumnInfo("task_state") val taskState: Boolean,
    @ColumnInfo("created_at") val createdAt: String,
    @ColumnInfo("completed_at") val completedAt: String?
)

fun TaskModel.toEntity(): TaskEntity {
    return TaskEntity(
        id = this.id,
        taskName = this.taskName,
        taskState = this.taskState,
        createdAt = this.createdAt,
        completedAt = this.completedAt
    )
}