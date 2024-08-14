package com.project.room_hilt.database

import androidx.room.Entity

//Data entities that represent tables in your app's database.
@Entity(primaryKeys = ["playlistId", "songId"])
data class PlaylistSongCrossRef(
    val playlistId: Long,
    val songId: Long
)
