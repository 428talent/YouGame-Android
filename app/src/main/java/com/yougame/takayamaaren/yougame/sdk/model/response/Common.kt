package com.yougame.takayamaaren.yougame.sdk.model.response

import com.google.gson.annotations.SerializedName

data class CommonResponse(
        @SerializedName("Result")
        val result: Boolean
)