package com.example.extendedcodingexercise.data.network.user.address.geo

import com.squareup.moshi.Json

data class TypiGeo(
    @Json(name = "lat") val lat: String,
    @Json(name = "lng") val lng: String,
)
