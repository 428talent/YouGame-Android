package com.yougame.takayamaaren.yougame.services

import com.yougame.takayamaaren.yougame.sdk.ApiError
import com.yougame.takayamaaren.yougame.sdk.model.request.RegisterUserRequestBody
import com.yougame.takayamaaren.yougame.sdk.model.request.UserLoginRequestBody
import com.yougame.takayamaaren.yougame.sdk.model.response.AuthResponseBody
import com.yougame.takayamaaren.yougame.sdk.model.response.Container
import com.yougame.takayamaaren.yougame.sdk.model.response.Profile
import com.yougame.takayamaaren.yougame.sdk.model.response.User
import com.yougame.takayamaaren.yougame.ui.clients.ApiClient

object UserServices {
    suspend fun registerUser(username: String, password: String, email: String): User {
        return ApiClient.client.createUser(RegisterUserRequestBody(username, password, email)).await()
    }

    /**
     * user login
     * @author takayamaaren
     * @throws ApiError when error
     * @return authResponse
     *
     */
    suspend fun login(username: String, password: String): AuthResponseBody {
        try {
            val authResponseBody = ApiClient.client.login(UserLoginRequestBody(username, password)).await()
            return authResponseBody
        } catch (e: Throwable) {
            throw ApiError(e)
        }
    }

    /**
     * get profile list
     * @author takayamaaren
     * @throws ApiError when error
     */
    suspend fun getUserProfile(
            page: Int = 1,
            pageSize: Int = 10,
            vararg queryOption: Pair<String, String>
    ): Container<Profile> {
        try {
            val params = mutableMapOf<String, String>().apply {
                putAll(queryOption)
                put("page", page.toString())
                put("pageSize", pageSize.toString())
            }
            return ApiClient.client.getProfileList(params).await()
        } catch (e: Throwable) {
            throw ApiError(e)
        }
    }
}