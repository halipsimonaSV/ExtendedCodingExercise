package com.example.extendedcodingexercise.data.network

import com.example.extendedcodingexercise.data.network.user.address.TypiAddress
import com.example.extendedcodingexercise.data.network.user.company.TypiCompany
import com.example.extendedcodingexercise.domain.User
import com.squareup.moshi.Json

data class TypiUser(
    @Json(name = "id") val userId: Int,
    @Json(name = "name") val name: String,
    @Json(name = "company") val typiCompany: TypiCompany,
    @Json(name = "address") val typiAddress: TypiAddress,
    @Json(name = "email") val email: String,
    @Json(name = "phone") val phone: String,
    @Json(name = "website") val website: String,
)

fun List<TypiUser>.asDomainModel(): List<User> {
    return map {
        User(it.userId, it.name, it.typiCompany, it.typiAddress, it.email, it.phone, it.website)
    }
}
