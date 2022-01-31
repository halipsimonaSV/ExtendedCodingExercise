package com.example.extendedcodingexercise.domain

import com.example.extendedcodingexercise.data.database.user.DBUser

data class User(
    val userId: Int,
    val name: String,
    val companyName: String,
    val email: String,
    val phone: String,
    val website: String,
    val street: String,
    val suite: String,
    val city: String,
    val zipcode: String,
    val lat: String,
    val lng: String,
)

fun List<User>.asDBModel(): List<DBUser> {
    return map {
        DBUser(
            it.userId,
            it.name,
            it.companyName,
            it.email,
            it.phone,
            it.website,
            it.street,
            it.suite,
            it.city,
            it.zipcode,
            it.lat,
            it.lng
        )
    }
}

fun User.asDBModel(): DBUser = DBUser(
    userId,
    name,
    companyName,
    email,
    phone,
    website,
    street,
    suite,
    city,
    zipcode,
    lat,
    lng
)