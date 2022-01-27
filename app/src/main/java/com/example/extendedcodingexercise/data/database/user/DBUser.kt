package com.example.extendedcodingexercise.data.database.user

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

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