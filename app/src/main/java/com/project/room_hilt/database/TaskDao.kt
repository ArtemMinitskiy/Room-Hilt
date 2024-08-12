package com.project.room_hilt.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface TaskDao {
    @Query("SELECT * FROM task")
    suspend fun getAllTask() : List<Task>

    @Query("SELECT * FROM priority")
    suspend fun getAllPriority() : List<PriorityEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTask(task : Task)

    @Insert(entity = PriorityEntity::class, onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPriority(priority : PriorityEntity)

    @Delete
    suspend fun deleteTask(task : Task)

    @Query("SELECT task.taskId, priority_name, name, date FROM task\n" +
            "INNER JOIN priority ON task.priority_id = priority.id\n")
    fun getAllStatisticData(): List<TaskPriorityTuple>
}