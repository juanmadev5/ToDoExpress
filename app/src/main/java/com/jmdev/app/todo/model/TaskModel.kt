package com.jmdev.app.todo.model

import com.jmdev.app.todo.data.database.TaskEntity

data class TaskModel(
    val id: Int,
    val taskName: String,
    val taskState: Boolean,
    val createdAt: String,
    val completedAt: String?
)

fun TaskEntity.toModel(): TaskModel {
    return TaskModel(
        id = this.id,
        taskName = this.taskName,
        taskState = this.taskState,
        createdAt = this.createdAt,
        completedAt = this.completedAt
    )
}