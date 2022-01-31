package com.example.extendedcodingexercise.data.database.album

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "albums")
data class DBAlbum(
    @ColumnInfo(name = "user_id") val userId: Int,
    @PrimaryKey @ColumnInfo(name = "id") val id: Int,
    @ColumnInfo(name = "title") val title: String,
)
