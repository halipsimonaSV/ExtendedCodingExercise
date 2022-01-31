package com.example.extendedcodingexercise.features.user

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.example.extendedcodingexercise.data.database.user.DBUser
import com.example.extendedcodingexercise.data.database.user.UserDao
import com.example.extendedcodingexercise.data.database.user.asDomainModel
import com.example.extendedcodingexercise.data.network.RetrofitService
import com.example.extendedcodingexercise.data.network.asDomainModel
import com.example.extendedcodingexercise.domain.User
import com.example.extendedcodingexercise.domain.asDBModel
import com.example.extendedcodingexercise.util.ActiveNetwork
import dagger.hilt.android.scopes.ActivityScoped
import retrofit2.HttpException
import javax.inject.Inject
import javax.inject.Singleton

interface UserRepository {
    fun getUser(userId: Int): LiveData<User>
    suspend fun refresh(userId: Int)
}

@Singleton
class UserRepositoryImp @Inject constructor(
    private val userDao: UserDao,
    private val retrofitService: RetrofitService,
    private val activeNetwork: ActiveNetwork
) : UserRepository {

    override fun getUser(userId: Int): LiveData<User> {
        return Transformations.map(userDao.getUser(userId)) {
            it.asDomainModel()
        }
    }

    override suspend fun refresh(userId: Int) {
        if (activeNetwork.isInternetAvailable()) {
            try {
                val user = retrofitService.fetchUserFromNetwork(
                    userId
                )
                insertIntoDB(
                    user.asDomainModel()
                )
            } catch (ex: HttpException) {
                throw ex
            }
        } else if (userDao.getUser(userId).value == null) {
            throw IllegalArgumentException("")
        }
    }

    private suspend fun insertIntoDB(user: User) {
        userDao.deleteThenInsertUserTransaction(
            user.asDBModel()
        )
    }
}