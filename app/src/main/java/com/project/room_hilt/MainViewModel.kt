package com.project.room_hilt

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.project.room_hilt.database.Library
import com.project.room_hilt.database.Playlist
import com.project.room_hilt.database.PlaylistSongCrossRef
import com.project.room_hilt.database.Song
import com.project.room_hilt.database.Task
import com.project.room_hilt.database.TaskPriorityTuple
import com.project.room_hilt.database.TaskRepository
import com.project.room_hilt.database.User
import com.project.room_hilt.database.UserRepository
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
    private val userRepository: UserRepository,
) : ViewModel() {

    fun addUsers() {
        viewModelScope.launch {
            for (i in 1..5) {
                userRepository.insertUser(User(name = "Artem $i", age = i))
            }
        }.invokeOnCompletion {
            viewModelScope.launch {
                userRepository.getAllUsers().forEach {
                    Log.e("mLogHilt", "$it")
                }
            }
            Log.e("mLogHilt", "Users Added")
        }
    }

    private val libraryList = listOf("A", "B", "C", "D", "E")

    fun addLibraries() {
        viewModelScope.launch {
            libraryList.forEachIndexed { index, library ->
                userRepository.insertLibrary(Library(userOwnerId = (index + 1).toLong(), libraryName = library))
            }
        }.invokeOnCompletion {
            viewModelScope.launch {
                userRepository.getAllLibrary().forEach {
                    Log.e("mLogHilt", "$it")
                }
            }
            Log.e("mLogHilt", "Libraries Added")
        }
    }

    private val playlistList = listOf("F", "G", "H", "I", "K")
    fun addPlaylists() {
        viewModelScope.launch {
            playlistList.forEachIndexed { index, playlist ->
                if (index + 1 == 3) {
                    userRepository.insertPlaylist(Playlist(userCreatorId = (index + 1).toLong(), playlistName = playlist))
                }
                userRepository.insertPlaylist(Playlist(userCreatorId = (index + 1).toLong(), playlistName = playlist))
            }
        }.invokeOnCompletion {
            viewModelScope.launch {
                userRepository.getAllPlaylist().forEach {
                    Log.e("mLogHilt", "$it")
                }
            }
            Log.e("mLogHilt", "Playlists Added")
        }
    }

    private val songsList = listOf("L", "M", "N", "O", "P")
    fun addSongs() {
        viewModelScope.launch {
            songsList.forEachIndexed { index, song ->
                userRepository.insertSong(Song(songName = song, artist = "Ne Artem $index"))
                if (index + 1 == 4) {
                    userRepository.insertSong(Song(songName = song, artist = "Ne Artem $index"))
                }
            }
        }.invokeOnCompletion {
            viewModelScope.launch {
                userRepository.getAllSongs().forEach {
                    Log.e("mLogHilt", "$it")
                }
            }
            Log.e("mLogHilt", "Songs Added")
        }
    }

    fun addCrossRef() {
        viewModelScope.launch {
            songsList.forEachIndexed { index, song ->
                for (i in 1..10) {
                    userRepository.insertCrossRef(PlaylistSongCrossRef(Random.nextInt(1, 5).toLong(), Random.nextInt(1, 5).toLong()))
                }
            }
        }.invokeOnCompletion {
            Log.e("mLogHilt", "CrossRef Added")
        }
    }

    fun getUsersAndLibraries() {
        viewModelScope.launch {
            userRepository.getUsersAndLibraries().forEach {
                Log.i("mLogHilt", "$it")
            }
        }
    }

    fun getUsersWithPlaylists() {
        viewModelScope.launch {
            userRepository.getUsersWithPlaylists().forEach {
                Log.i("mLogHilt", "$it")
            }
        }
    }

    fun getPlaylistsWithSongs() {
        viewModelScope.launch {
            userRepository.getPlaylistsWithSongs().forEach {
                Log.i("mLogHilt", "$it")
            }
        }
    }

    fun getSongsWithPlaylists() {
        viewModelScope.launch {
            userRepository.getSongsWithPlaylists().forEach {
                Log.i("mLogHilt", "$it")
            }
        }
    }

    fun getUsersWithPlaylistsAndSongs() {
        viewModelScope.launch {
            userRepository.getUsersWithPlaylistsAndSongs().forEach {
                Log.i("mLogHilt", "$it")
            }
        }
    }





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