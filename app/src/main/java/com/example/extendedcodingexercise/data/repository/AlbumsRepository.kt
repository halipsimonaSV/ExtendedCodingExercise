package com.example.extendedcodingexercise.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.example.extendedcodingexercise.data.database.albumuser.AlbumUserDao
import com.example.extendedcodingexercise.data.database.album.AlbumDao
import com.example.extendedcodingexercise.data.database.albumuser.asDomainModel
import com.example.extendedcodingexercise.data.database.user.UserDao
import com.example.extendedcodingexercise.data.network.RetrofitService
import com.example.extendedcodingexercise.data.network.album.asDomainModel
import com.example.extendedcodingexercise.data.network.asDomainModel
import com.example.extendedcodingexercise.domain.Album
import com.example.extendedcodingexercise.domain.User
import com.example.extendedcodingexercise.domain.asDBModel
import com.example.extendedcodingexercise.util.ActiveNetwork
import retrofit2.HttpException
import javax.inject.Inject
import javax.inject.Singleton

interface AlbumsRepository {
    suspend fun getAlbums()
    val albumList: LiveData<List<Album>>
}

@Singleton
class AlbumsRepositoryImpl @Inject constructor(
    albumUserDao: AlbumUserDao,
    private val albumDao: AlbumDao,
    private val userDao: UserDao,
    private val retrofitService: RetrofitService,
    private val activeNetwork: ActiveNetwork,
) : AlbumsRepository {

    override val albumList: LiveData<List<Album>> =
        Transformations.map(albumUserDao.getAlbumUsers()) {
            it.asDomainModel()
        }

    override suspend fun getAlbums() {
        if (activeNetwork.isInternetAvailable()) {
            try {
                val typiAlbums = retrofitService.fetchAlbumsFromNetwork()
                val typiUsers = retrofitService.fetchUsersFromNetwork()
                if (typiAlbums.isNotEmpty()) {
                    insertAlbumsIntoDB(typiAlbums.asDomainModel())
                }
                if (typiUsers.isNotEmpty()){
                    insertUsersIntoDB(typiUsers.asDomainModel())
                }
            } catch (ex: HttpException) {
                throw ex
            }
        } else if (albumDao.getAlbumsCount() == 0) {
            throw IllegalArgumentException("")
        }
    }

    private suspend fun insertAlbumsIntoDB(albumsToInsert: List<Album>) {
        albumDao.deleteThenInsertAlbumsTransaction(albumsToInsert.asDBModel())
    }

    private suspend fun insertUsersIntoDB(usersToInsert: List<User>) {
        userDao.deleteThenInsertUsersTransaction(usersToInsert.asDBModel())
    }
}
