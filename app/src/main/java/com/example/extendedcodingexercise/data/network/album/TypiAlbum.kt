package com.example.extendedcodingexercise.data.network.album

import com.example.extendedcodingexercise.domain.Album
import com.squareup.moshi.Json

data class TypiAlbum(
    @Json(name = "userId") val userId: Int,
    @Json(name = "id") val id: Int,
    @Json(name = "title") val title: String,
)

fun List<TypiAlbum>.asDomainModel(): List<Album> {
    return map {
        Album(it.userId, it.id, it.title, "")
    }
}