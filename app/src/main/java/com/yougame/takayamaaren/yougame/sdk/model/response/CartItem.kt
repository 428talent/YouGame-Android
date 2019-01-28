package com.yougame.takayamaaren.yougame.sdk.model.response

import com.google.gson.annotations.SerializedName
import java.util.*


data class CartItem(
        val created: Date,
        @SerializedName("good_id")
        val goodId: Int,
        val id: Int,
        @SerializedName("user_id")
        val userId: Int
)