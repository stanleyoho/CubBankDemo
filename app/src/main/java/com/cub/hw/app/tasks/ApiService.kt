package com.cub.hw.app.tasks

import com.cub.hw.app.apiResponseModel.AreasDetailInformation
import com.cub.hw.app.apiResponseModel.PlantDetailInformation
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    @GET("5a0e5fbb-72f8-41c6-908e-2fb25eff9b8a?scope=resourceAquire")
    fun GetTaipeiZooAreasData(): Deferred<Response<AreasDetailInformation>>

    @GET("f18de02f-b6c9-47c0-8cda-50efad621c14?scope=resourceAquire&limit=200")
    fun GetTaipeiZooPlantData(): Deferred<Response<PlantDetailInformation>>
}