package com.project.room_hilt.database

import androidx.room.ColumnInfo

data class TaskPriorityTuple(
    val taskId: Int,
    @ColumnInfo(name = "priority_name") val priorityName: String,
    val name: String,
    val date: String
)