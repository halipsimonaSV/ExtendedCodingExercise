package com.example.extendedcodingexercise.data.database.albumuser

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.PrimaryKey
import androidx.room.Relation
import com.example.extendedcodingexercise.data.database.album.DBAlbum
import com.example.extendedcodingexercise.data.database.user.DBUser
import com.example.extendedcodingexercise.domain.Album

data class DBAlbumUser(
    val name: String,
    val user_id: Int,
    val id: Int,
    val title: String,
)

fun List<DBAlbumUser>.asDomainModel(): List<Album> {
    return map { albumusers ->
        Album(albumusers.user_id, albumusers.id, albumusers.title,
            albumusers.name
                .split(' ')
                .mapNotNull { it.firstOrNull()?.toString() }
                .reduce { acc, s -> acc + s })
    }
}