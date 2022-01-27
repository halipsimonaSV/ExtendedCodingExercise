package com.example.extendedcodingexercise.data.database.user

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction

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
}