package com.example.extendedcodingexercise.data.database.albumuser

import androidx.room.Embedded
import androidx.room.Relation
import com.example.extendedcodingexercise.data.database.album.DBAlbum
import com.example.extendedcodingexercise.data.database.user.DBUser
import com.example.extendedcodingexercise.domain.Album

data class DBAlbumUser(
    @Embedded val dbUser: DBUser,
    @Relation(parentColumn = "user_id", entityColumn = "id")
    val dbAlbum: DBAlbum
)

fun List<DBAlbumUser>.asDomainModel(): List<Album> {
    return map { albumusers ->
        Album(albumusers.dbAlbum.userId, albumusers.dbAlbum.id, albumusers.dbAlbum.title,
            albumusers.dbUser.name
                .split(' ')
                .mapNotNull { it.firstOrNull()?.toString() }
                .reduce { acc, s -> acc + s })
    }
}