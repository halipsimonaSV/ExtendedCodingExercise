package com.example.extendedcodingexercise.data.network.user.company

import com.squareup.moshi.Json

data class TypiCompany(@Json(name = "name") val companyName: String)