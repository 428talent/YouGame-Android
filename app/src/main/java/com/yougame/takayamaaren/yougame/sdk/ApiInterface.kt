package com.yougame.takayamaaren.yougame.sdk

import com.yougame.takayamaaren.yougame.sdk.model.request.RegisterUserRequestBody
import com.yougame.takayamaaren.yougame.sdk.model.request.UserLoginRequestBody
import com.yougame.takayamaaren.yougame.sdk.model.response.AuthResponseBody
import com.yougame.takayamaaren.yougame.sdk.model.response.Container
import com.yougame.takayamaaren.yougame.sdk.model.response.Profile
import com.yougame.takayamaaren.yougame.sdk.model.response.User
import kotlinx.coroutines.Deferred
import okhttp3.MultipartBody
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*


interface ApiInterface {
    @POST("/api/users")
    fun createUser(
            @Body request: RegisterUserRequestBody
    ): Deferred<User>

    @POST("/api/user/auth")
    fun login(
            @Body request: UserLoginRequestBody
    ): Deferred<AuthResponseBody>

    @GET("/api/profile")
    fun getProfileList(
            @QueryMap queryMap: Map<String, String>
    ): Deferred<Container<Profile>>
}