package com.yougame.takayamaaren.yougame.sdk.model.response

import com.google.gson.annotations.SerializedName


data class User(
        @SerializedName("create_at")
        val createAt: String,
        val id: Int,
        @SerializedName("last_login")
        val lastLogin: String,
        val username: String
)

data class AuthResponseBody(
        val payload: Payload,
        val success: Boolean
) {
    data class Payload(
            @SerializedName("Sign")
            val sign: String,
            @SerializedName("UserId")
            val userId: Int
    )
}