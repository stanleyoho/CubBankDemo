package com.cub.hw.app.apiResponseModel


import com.google.gson.annotations.SerializedName

data class Importdate(
    @SerializedName("date")
    val date: String,
    @SerializedName("timezone")
    val timezone: String,
    @SerializedName("timezone_type")
    val timezoneType: Int
)