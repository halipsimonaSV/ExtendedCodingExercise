package com.example.extendedcodingexercise.data.network

import com.example.extendedcodingexercise.data.network.album.TypiAlbum
import retrofit2.http.GET
import retrofit2.http.Path

interface RetrofitService {
    @GET("albums")
    suspend fun fetchAlbumsFromNetwork(): List<TypiAlbum>

    @GET("users")
    suspend fun fetchUsersFromNetwork(): List<TypiUser>

    @GET("users/{uid}")
    suspend fun fetchUserFromNetwork(@Path("uid") uid: Int): TypiUser
}
