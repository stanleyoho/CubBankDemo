package com.cub.hw.app.apiResponseModel


import com.google.gson.annotations.SerializedName

data class ResultXX(
    @SerializedName("count")
    val count: Int,
    @SerializedName("limit")
    val limit: Int,
    @SerializedName("offset")
    val offset: Int,
    @SerializedName("results")
    val results: List<ResultXXX>,
    @SerializedName("sort")
    val sort: String
)