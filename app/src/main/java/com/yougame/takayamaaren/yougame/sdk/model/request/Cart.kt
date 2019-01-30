package com.yougame.takayamaaren.yougame.sdk.model.request

import com.google.gson.annotations.SerializedName

data class AddCartRequestBody(
        @SerializedName("good_id")
        val goodId: Int
)