package com.project.room_hilt.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "task",
    indices = [Index("taskId")],
    foreignKeys = [
        ForeignKey(
            entity = PriorityEntity::class,
            parentColumns = ["id"],
            childColumns = ["priority_id"]
        )
    ]
)
data class Task(
    @PrimaryKey(autoGenerate = true)
    val taskId: Int? = 0,
    val name: String,
    @ColumnInfo(name = "priority_id") val priorityId: Long,
    val date: String
)