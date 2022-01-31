package com.example.extendedcodingexercise.data.database.album

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface AlbumDao {
    @Query("SELECT *  FROM albums")
    fun getAlbums(): LiveData<List<DBAlbum>>

    @Query("DELETE FROM albums")
    suspend fun deleteAlbums()

    @Insert
    suspend fun insertAlbums(albums: List<DBAlbum>)

    @Transaction
    suspend fun deleteThenInsertAlbumsTransaction(albums: List<DBAlbum>) {
        deleteAlbums()
        insertAlbums(albums)
    }

    @Query("SELECT COUNT(*) from albums")
    suspend fun getAlbumsCount(): Int
}