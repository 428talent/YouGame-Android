package com.yougame.takayamaaren.yougame.sdk.model.request

import com.google.gson.annotations.SerializedName

data class RegisterUserRequestBody(
        val username: String,
        val password: String,
        val email: String
)

data class UserLoginRequestBody(
        @SerializedName("login_name")
        val loginName: String,
        val password: String
)