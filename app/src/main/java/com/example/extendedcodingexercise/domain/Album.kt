package com.example.extendedcodingexercise.domain

import com.example.extendedcodingexercise.data.database.album.DBAlbum

data class Album(
    val userId: Int,
    val id: Int,
    val title: String,
    val userInitials: String
)

fun List<Album>.asDBModel(): List<DBAlbum> {
    return map {
        DBAlbum(it.userId, it.id, it.title)
    }
}
