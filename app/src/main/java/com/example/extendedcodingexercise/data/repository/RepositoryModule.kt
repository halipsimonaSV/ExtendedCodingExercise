package com.example.extendedcodingexercise.data.repository

import com.example.extendedcodingexercise.features.user.UserRepository
import com.example.extendedcodingexercise.features.user.UserRepositoryImp
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
abstract class RepositoryModule {
    @Binds
    abstract fun bindAlbumsRepo(albumsRepositoryImpl: AlbumsRepositoryImpl): AlbumsRepository

    @Binds
    abstract fun bindUserRepo(userRepositoryImp: UserRepositoryImp): UserRepository
}
