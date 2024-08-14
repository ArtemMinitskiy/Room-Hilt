package com.project.room_hilt.database

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation

data class PlaylistWithSongs(
    //Sometimes, you'd like to express an entity or data object as a cohesive whole in your database logic, even if the object contains several fields. In these situations, you can use the @Embedded annotation to represent an object that you'd like to decompose into its subfields within a table.
    //You can then query the embedded fields just as you do for other individual columns.
    @Embedded val playlist: Playlist,
    //A convenience annotation which can be used in a POJO to automatically fetch relation entities.
    //When the POJO is returned from a query, all of its relations are also fetched by Room.
    @Relation(
        parentColumn = "playlistId",
        entityColumn = "songId",
        associateBy = Junction(PlaylistSongCrossRef::class)
    )
    val songs: List<Song>
)
