package com.yougame.takayamaaren.yougame.sdk.model.request

data class CreateUserRequest(
        val Username: String,
        val Password: String,
        val email: String
)