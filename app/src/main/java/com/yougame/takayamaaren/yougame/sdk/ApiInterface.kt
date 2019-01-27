package com.yougame.takayamaaren.yougame.sdk

import com.yougame.takayamaaren.yougame.sdk.model.request.CreateUserRequest
import com.yougame.takayamaaren.yougame.sdk.model.response.CommonResponse
import io.reactivex.Observable
import okhttp3.MultipartBody
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part


interface ApiInterface {
    @POST("/v1/user")
    fun createUser(
            @Body request: CreateUserRequest
    ): Observable<CommonResponse>

    @Multipart
    @POST("upload")
    fun upload(
            @Part avatar: MultipartBody.Part
    ): Call<ResponseBody>
}