package com.yougame.takayamaaren.yougame.sdk.model.response
import com.google.gson.annotations.SerializedName


data class Game(
    val id: Int,
    val intro: String,
    val name: String,
    val price: String,
    val publisher: String,
    @SerializedName("release_time")
    val releaseTime: String
)