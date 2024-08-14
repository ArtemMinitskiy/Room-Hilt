package com.project.room_hilt.database

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class UserRepository @Inject constructor(private val userDao: UserDao) {

    suspend fun insertUser(user: User) {
        withContext(Dispatchers.IO) {
            userDao.insertUser(user)
        }
    }

    suspend fun getAllUsers(): List<User> {
        return withContext(Dispatchers.IO) {
            return@withContext userDao.getAllUsers()
        }
    }

    suspend fun insertLibrary(library: Library) {
        withContext(Dispatchers.IO) {
            userDao.insertLibrary(library)
        }
    }

    suspend fun getAllLibrary(): List<Library> {
        return withContext(Dispatchers.IO) {
            return@withContext userDao.getAllLibrary()
        }
    }

    suspend fun insertPlaylist(playlist: Playlist) {
        withContext(Dispatchers.IO) {
            userDao.insertPlaylist(playlist)
        }
    }

    suspend fun getAllPlaylist(): List<Playlist> {
        return withContext(Dispatchers.IO) {
            return@withContext userDao.getAllPlaylist()
        }
    }

    suspend fun insertSong(song: Song) {
        withContext(Dispatchers.IO) {
            userDao.insertSong(song)
        }
    }

    suspend fun insertCrossRef(crossRef: PlaylistSongCrossRef) {
        withContext(Dispatchers.IO) {
            userDao.insertCrossRef(crossRef)
        }
    }

    suspend fun getAllSongs(): List<Song> {
        return withContext(Dispatchers.IO) {
            return@withContext userDao.getAllSongs()
        }
    }

    suspend fun getUsersAndLibraries(): List<UserAndLibrary> {
        return withContext(Dispatchers.IO) {
            return@withContext userDao.getUsersAndLibraries()
        }
    }

    suspend fun getUsersWithPlaylists(): List<UserWithPlaylists> {
        return withContext(Dispatchers.IO) {
            return@withContext userDao.getUsersWithPlaylists()
        }
    }

    suspend fun getPlaylistsWithSongs(): List<PlaylistWithSongs> {
        return withContext(Dispatchers.IO) {
            return@withContext userDao.getPlaylistsWithSongs()
        }
    }

    suspend fun getSongsWithPlaylists(): List<SongWithPlaylists> {
        return withContext(Dispatchers.IO) {
            return@withContext userDao.getSongsWithPlaylists()
        }
    }

    suspend fun getUsersWithPlaylistsAndSongs(): List<UserWithPlaylistsAndSongs> {
        return withContext(Dispatchers.IO) {
            return@withContext userDao.getUsersWithPlaylistsAndSongs()
        }
    }
}