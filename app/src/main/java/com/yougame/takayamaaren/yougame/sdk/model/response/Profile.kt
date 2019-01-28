package com.yougame.takayamaaren.yougame.sdk.model.response

import com.google.gson.annotations.SerializedName


data class Profile(
        val avatar: String,
        val email: String,
        val nickname: String,
        @SerializedName("update_at")
        val updateAt: String,
        @SerializedName("user_id")
        val userId: Int
)