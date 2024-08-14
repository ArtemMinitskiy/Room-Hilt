package com.project.room_hilt.database

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class TaskRepository @Inject constructor(private val taskDao: TaskDao) {
    suspend fun getAllTask(): List<Task> {
        return withContext(Dispatchers.IO) {
            return@withContext taskDao.getAllTask()
        }
    }

    suspend fun getAllStatisticData(): List<TaskPriorityTuple> {
        return withContext(Dispatchers.IO) {
            return@withContext taskDao.getAllStatisticData()
        }
    }

    suspend fun insertTask(task: Task) {
        withContext(Dispatchers.IO) {
            taskDao.insertTask(task)
        }
    }

    suspend fun insertPriority(priority: String) {
        withContext(Dispatchers.IO) {
            taskDao.insertPriority(PriorityEntity(priorityName = priority))
        }
    }

    suspend fun getAllPriority(): List<PriorityEntity> {
        return withContext(Dispatchers.IO) {
            return@withContext taskDao.getAllPriority()
        }
    }

    suspend fun deleteTask(task: Task) {
        return taskDao.deleteTask(task)
    }
}