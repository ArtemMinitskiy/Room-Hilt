package com.project.room_hilt.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

//Data entities that represent tables in your app's database.
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
    //Each Room entity must define a primary key that uniquely identifies each row in the corresponding database table. The most straightforward way of doing this is to annotate a single column with @PrimaryKey
    @PrimaryKey(autoGenerate = true)
    val taskId: Int? = 0,
    val name: String,
    @ColumnInfo(name = "priority_id") val priorityId: Long,
    val date: String
)