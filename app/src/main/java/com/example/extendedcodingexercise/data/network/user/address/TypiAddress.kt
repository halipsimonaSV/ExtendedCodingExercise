package com.example.extendedcodingexercise.data.network.user.address

import com.example.extendedcodingexercise.data.network.user.address.geo.TypiGeo
import com.squareup.moshi.Json

data class TypiAddress(
    @Json(name = "street") val street: String,
    @Json(name = "suite") val suite: String,
    @Json(name = "city") val city: String,
    @Json(name = "zipcode") val zipcode: String,
    @Json(name = "geo") val typiGeo: TypiGeo
)
