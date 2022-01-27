package com.example.extendedcodingexercise.data.network

import com.example.extendedcodingexercise.data.network.album.TypiAlbum
import retrofit2.http.GET

interface RetrofitService {
    @GET("albums")
    suspend fun fetchAlbumsFromNetwork(): List<TypiAlbum>

    @GET("users")
    suspend fun fetchUsersFromNetwork(): List<TypiUser>
}
