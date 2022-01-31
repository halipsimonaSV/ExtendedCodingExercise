package com.example.extendedcodingexercise.data.database

import android.content.Context
import com.example.extendedcodingexercise.data.database.album.AlbumDao
import com.example.extendedcodingexercise.data.database.albumuser.AlbumUserDao
import com.example.extendedcodingexercise.data.database.user.UserDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DatabaseModule {
    @Singleton
    @Provides
    fun provideAlbumDao(database: DatabaseManager): AlbumDao {
        return database.getAlbumDao()
    }

    @Singleton
    @Provides
    fun provideUserDao(database: DatabaseManager): UserDao {
        return database.getUserDao()
    }

    @Singleton
    @Provides
    fun provideAddressDao(database: DatabaseManager): AlbumUserDao {
        return database.getAlbumUserDao()
    }

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext appContext: Context): DatabaseManager {
        return DatabaseManager.getInstance(appContext)
    }
}