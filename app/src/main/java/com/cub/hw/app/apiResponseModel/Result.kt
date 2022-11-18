package com.cub.hw.app.apiResponseModel


import com.google.gson.annotations.SerializedName

data class Result(
    @SerializedName("count")
    val count: Int,
    @SerializedName("limit")
    val limit: Int,
    @SerializedName("offset")
    val offset: Int,
    @SerializedName("results")
    val results: List<ResultX>,
    @SerializedName("sort")
    val sort: String
)