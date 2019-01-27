package com.talent428.yougame.sdk

import com.talent428.yougame.sdk.model.request.CreateUserRequest
import com.talent428.yougame.sdk.model.response.CommonResponse
import io.reactivex.Observable
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiInterface {
    @POST("/user")
    fun CreateUser(
            @Body request: CreateUserRequest
    ): Observable<CommonResponse>
}