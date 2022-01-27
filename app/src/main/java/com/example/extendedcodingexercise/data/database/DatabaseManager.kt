package com.example.extendedcodingexercise.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.extendedcodingexercise.data.database.album.AlbumDao
import com.example.extendedcodingexercise.data.database.album.DBAlbum
import com.example.extendedcodingexercise.data.database.albumuser.AlbumUserDao
import com.example.extendedcodingexercise.data.database.user.*

@Database(exportSchema = false, version = 4, entities = [DBAlbum::class, DBUser::class])
abstract class DatabaseManager : RoomDatabase() {
    companion object {
        private const val DB_NAME = "albums_posts_db"
        fun getInstance(context: Context): DatabaseManager =
            Room.databaseBuilder(context, DatabaseManager::class.java, DB_NAME)
                .fallbackToDestructiveMigration().build()
    }

    abstract fun getAlbumDao(): AlbumDao
    abstract fun getUserDao(): UserDao
    abstract fun getAlbumUserDao(): AlbumUserDao
}