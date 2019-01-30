package com.yougame.takayamaaren.yougame.sdk.model.response

import com.google.gson.annotations.SerializedName


data class Good(
        @SerializedName("game_id")
        val gameId: Int,
        val id: Int,
        val name: String,
        val price: Double
)