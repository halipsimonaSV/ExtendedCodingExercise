package com.example.extendedcodingexercise.data.database.user

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.example.extendedcodingexercise.data.network.user.company.TypiCompany
import com.example.extendedcodingexercise.domain.User

@Entity(tableName = "users")
data class DBUser(
    @PrimaryKey @ColumnInfo(name = "user_id") val userid: Int,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "company_name") val companyName: String,
    @ColumnInfo(name = "email") val email: String,
    @ColumnInfo(name = "phone") val phone: String,
    @ColumnInfo(name = "website") val website: String,
    @ColumnInfo(name = "street") val street: String,
    @ColumnInfo(name = "suite") val suite: String,
    @ColumnInfo(name = "city") val city: String,
    @ColumnInfo(name = "zipcode") val zipcode: String,
    @ColumnInfo(name = "lat") val lat: String,
    @ColumnInfo(name = "lng") val lng: String,
)

fun List<DBUser>.asDomainModel(): List<User> {
    return map {
        User(
            it.userid,
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

fun DBUser.asDomainModel(): User =
    User(
        userid,
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
