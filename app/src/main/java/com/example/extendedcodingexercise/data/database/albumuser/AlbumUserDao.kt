package com.example.extendedcodingexercise.data.database.albumuser

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import androidx.room.RewriteQueriesToDropUnusedColumns
import androidx.room.Transaction

@Dao
interface AlbumUserDao {
    @Transaction
    @Query("SELECT albums.user_id,albums.id,albums.title,users.name FROM users INNER JOIN albums ON albums.user_id=users.user_id")
    fun getAlbumUsers(): LiveData<List<DBAlbumUser>>
}
