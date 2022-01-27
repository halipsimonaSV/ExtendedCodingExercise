package com.example.extendedcodingexercise.domain

import com.example.extendedcodingexercise.data.database.user.DBUser
import com.example.extendedcodingexercise.data.network.user.address.TypiAddress
import com.example.extendedcodingexercise.data.network.user.company.TypiCompany

data class User(
    val userId: Int,
    val name: String,
    val typiCompany: TypiCompany,
    val typiAddress: TypiAddress,
    val email: String,
    val phone: String,
    val website: String,
)

fun List<User>.asDBModel(): List<DBUser> {
    return map {
        DBUser(
            it.userId,
            it.name,
            it.typiCompany.companyName,
            it.email,
            it.phone,
            it.website,
            it.typiAddress.street,
            it.typiAddress.suite,
            it.typiAddress.city,
            it.typiAddress.zipcode,
            it.typiAddress.typiGeo.lat,
            it.typiAddress.typiGeo.lng
        )
    }
}
