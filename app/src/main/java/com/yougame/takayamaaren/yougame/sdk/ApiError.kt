package com.yougame.takayamaaren.yougame.sdk

import com.google.gson.Gson
import retrofit2.HttpException
import java.lang.Exception

class ApiError(e: Throwable) : Exception() {
    lateinit var code: String
    lateinit var error: String
    lateinit var detail: String

    data class ErrorResponse(
            val code: String,
            val error: String,
            val detail: String

    )

    init {
        if (e is HttpException) {
            val errorJsonString = e.response()
                    .errorBody()?.string()
            val errorResponse = Gson().fromJson(errorJsonString, ErrorResponse::class.java)
            code = errorResponse.code
            error = errorResponse.error
            detail = errorResponse.detail

        } else {
            this.error = e.message ?: this.error
        }
    }
}