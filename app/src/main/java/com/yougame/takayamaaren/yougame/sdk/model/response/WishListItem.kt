package com.yougame.takayamaaren.yougame.sdk.model.response

import com.google.gson.annotations.SerializedName
import java.util.*


data class WishListItem(
        val created: Date,
        @SerializedName("game_id")
        val gameId: Int,
        val id: Int,
        @SerializedName("user_id")
        val userId: Int
)