package com.example.extendedcodingexercise.data.database.albumuser

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import androidx.room.RewriteQueriesToDropUnusedColumns
import androidx.room.Transaction

@Dao
interface AlbumUserDao {
    @Transaction
    @RewriteQueriesToDropUnusedColumns
    @Query("SELECT * FROM users INNER JOIN albums ON albums.user_id=users.user_id")
    fun getAlbumUsers(): LiveData<List<DBAlbumUser>>
}
