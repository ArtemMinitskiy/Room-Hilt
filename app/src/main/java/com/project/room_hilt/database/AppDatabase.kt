package com.project.room_hilt.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Task::class, PriorityEntity::class], version = 2, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun taskDao(): TaskDao
}