package com.talent428.yougame.sdk.model.response

import com.google.gson.annotations.SerializedName

data class CommonResponse(
        @SerializedName("Result")
        val result: Boolean
)