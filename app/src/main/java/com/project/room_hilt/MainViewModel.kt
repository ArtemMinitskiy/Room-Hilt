package com.project.room_hilt

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.project.room_hilt.database.TaskPriorityTuple
import com.project.room_hilt.database.Task
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.random.Random

//В Hilt для ViewModel есть специальная аннотация @HiltViewModel, ViewModel также как и раньше будут привязаны к жизненным циклам Fragment или Activity, смотря где вы захотите её создать.
@HiltViewModel
//This is called constructor injection. The @Inject keyword comes from the Hilt, which enables the framework to find this constructor and inject it with appropriate class instances.
class MainViewModel @Inject constructor(
    private val taskRepository: TaskRepository,
) : ViewModel() {

    private val _tasksFlow = MutableStateFlow<List<Task>>(listOf())
    val tasksFlow: StateFlow<List<Task>> = _tasksFlow.asStateFlow()

    private val _taskPriorityTuple = MutableStateFlow<List<TaskPriorityTuple>>(listOf())
    val taskPriorityTuple: StateFlow<List<TaskPriorityTuple>> = _taskPriorityTuple.asStateFlow()

    init {
        loadTask()
    }

    private fun loadTask() {
        viewModelScope.launch {
            _tasksFlow.value = taskRepository.getAllTask()
            _taskPriorityTuple.value = taskRepository.getAllStatisticData()
        }
    }


    private val priorityList = listOf("LOW", "MEDIUM", "HIGH")

    fun addTask() {
        viewModelScope.launch {
            for (i in 1..5) {
                taskRepository.insertTask(
                    Task(
                        taskId = null, name = "$i", priorityId = Random.nextInt(1, 4).toLong(), date = "${i * Random.nextInt(1, 100)}"
                    )
                )
            }
        }.invokeOnCompletion {
            loadTask()
        }
    }

    fun addPriority() {
        viewModelScope.launch {
            priorityList.forEach {
                taskRepository.insertPriority(it)
            }
        }.invokeOnCompletion {
            viewModelScope.launch {
                taskRepository.getAllPriority().forEach {
                    Log.i("mLogHilt", "Priority: $it")
                }
            }
        }
    }
}