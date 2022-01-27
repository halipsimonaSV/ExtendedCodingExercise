package com.example.extendedcodingexercise.data.repository

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
abstract class RepositoryModule {
    @Binds
    abstract fun bindAlbumsRepo(albumsRepositoryImpl: AlbumsRepositoryImpl): AlbumsRepository
}
