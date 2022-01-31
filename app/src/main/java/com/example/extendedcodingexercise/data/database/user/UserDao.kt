package com.example.extendedcodingexercise.data.database.user

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.extendedcodingexercise.domain.User

@Dao
interface UserDao {
    @Query("SELECT * FROM users")
    fun getUsers(): LiveData<List<DBUser>>

    @Query("DELETE FROM users")
    suspend fun deleteUsers()

    @Insert
    suspend fun insertUsers(users: List<DBUser>)

    @Transaction
    suspend fun deleteThenInsertUsersTransaction(users: List<DBUser>) {
        deleteUsers()
        insertUsers(users)
    }

    @Query("SELECT * FROM users WHERE user_id=:userId")
    fun getUser(userId: Int): LiveData<DBUser>

    @Query("DELETE FROM users WHERE user_id=:userId")
    suspend fun deleteUser(userId: Int)

    @Insert
    suspend fun insertUser(user: DBUser)

    @Transaction
    suspend fun deleteThenInsertUserTransaction(user: DBUser) {
        deleteUser(user.userid)
        insertUser(user)
    }
}