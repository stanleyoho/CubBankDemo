package com.cub.hw.app.apiResponseModel


import com.google.gson.annotations.SerializedName

data class ResultX(
    @SerializedName("e_category")
    val eCategory: String,
    @SerializedName("e_geo")
    val eGeo: String,
    @SerializedName("e_info")
    val eInfo: String,
    @SerializedName("e_memo")
    val eMemo: String,
    @SerializedName("e_name")
    val eName: String,
    @SerializedName("e_no")
    val eNo: String,
    @SerializedName("e_pic_url")
    val ePicUrl: String,
    @SerializedName("e_url")
    val eUrl: String,
    @SerializedName("_id")
    val id: Int,
    @SerializedName("_importdate")
    val importdate: Importdate
)