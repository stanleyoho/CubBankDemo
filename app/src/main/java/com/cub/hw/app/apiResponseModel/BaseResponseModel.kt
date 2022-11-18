package com.cub.hw.app.apiResponseModel

class BaseResponseModel {
    var result : Boolean = false
    var data : String = ""
    var msg : String = ""

    fun getSuccessResponse(responseData:String):BaseResponseModel{
        result = true
        data = responseData
        return this
    }

    fun getFailResponse(errMsg: String):BaseResponseModel{
        result = false
        msg = errMsg
        return this
    }
}