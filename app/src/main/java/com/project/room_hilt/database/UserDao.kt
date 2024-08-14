package com.project.room_hilt.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction

//Data access objects (DAOs) that provide methods that your app can use to query, update, insert, and delete data in the database.
@Dao
interface UserDao {
    @Insert(entity = User::class, onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user: User)

    @Query("SELECT * FROM User")
    suspend fun getAllUsers(): List<User>

    @Insert(entity = Library::class, onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertLibrary(library: Library)

    @Query("SELECT * FROM Library")
    suspend fun getAllLibrary(): List<Library>

    @Insert(entity = Playlist::class, onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPlaylist(playlist: Playlist)

    @Query("SELECT * FROM Playlist")
    suspend fun getAllPlaylist(): List<Playlist>

    @Insert(entity = Song::class, onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSong(song: Song)

    @Insert(entity = PlaylistSongCrossRef::class, onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCrossRef(cross: PlaylistSongCrossRef)

    @Query("SELECT * FROM Song")
    suspend fun getAllSongs(): List<Song>

    //One-to-one relationships
    //This method requires Room to run two queries, so add the @Transaction annotation to this method so that the whole operation is performed atomically.
    @Transaction
    @Query("SELECT * FROM User")
    fun getUsersAndLibraries(): List<UserAndLibrary>

    //One-to-many relationships
    //This method requires Room to run two queries, so add the @Transaction annotation to this method so that the whole operation is performed atomically.
    @Transaction
    @Query("SELECT * FROM User")
    fun getUsersWithPlaylists(): List<UserWithPlaylists>

    //Many-to-many relationships
    //This method requires Room to run two queries, so add the @Transaction annotation to this method so that the whole operation is performed atomically.
    @Transaction
    @Query("SELECT * FROM Playlist")
    fun getPlaylistsWithSongs(): List<PlaylistWithSongs>

    //Many-to-many relationships
    //This method requires Room to run two queries, so add the @Transaction annotation to this method so that the whole operation is performed atomically.
    @Transaction
    @Query("SELECT * FROM Song")
    fun getSongsWithPlaylists(): List<SongWithPlaylists>

    //Nested relationships
    //This method requires Room to run two queries, so add the @Transaction annotation to this method so that the whole operation is performed atomically.
    @Transaction
    @Query("SELECT * FROM User")
    fun getUsersWithPlaylistsAndSongs(): List<UserWithPlaylistsAndSongs>

}