package com.project.room_hilt.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

//Data entities that represent tables in your app's database.
@Entity(tableName = "priority")
data class PriorityEntity(
    //Each Room entity must define a primary key that uniquely identifies each row in the corresponding database table. The most straightforward way of doing this is to annotate a single column with @PrimaryKey
    @PrimaryKey(autoGenerate = true) val id: Long? = null,
    @ColumnInfo(name = "priority_name") val priorityName: String
)
