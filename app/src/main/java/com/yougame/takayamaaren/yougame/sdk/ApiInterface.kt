package com.yougame.takayamaaren.yougame.sdk

import com.yougame.takayamaaren.yougame.sdk.model.request.RegisterUserRequestBody
import com.yougame.takayamaaren.yougame.sdk.model.request.UserLoginRequestBody
import com.yougame.takayamaaren.yougame.sdk.model.response.*
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
            @Query("id") ids: List<Int>?,
            @Query("user") userIds: List<Int>?,
            @QueryMap queryMap: Map<String, String>
    ): Deferred<Container<Profile>>

    @GET("/api/carts")
    fun getCartList(

            @Header("Authorization") token: String,
            @QueryMap queryMap: Map<String, String>
    ): Deferred<Container<CartItem>>

    @GET("/api/goods")
    fun getGoodList(
            @Query("id") ids: List<Int>?,
            @Query("game") gameIds: List<Int>?,
            @QueryMap queryMap: Map<String, String>
    ): Deferred<Container<Good>>

    @GET("/api/games")
    fun getGameList(
            @Query("id") ids: List<Int>?,
            @QueryMap queryMap: Map<String, String>
    ): Deferred<Container<Game>>

    @GET("/api/game/{id}")
    fun getGame(
            @Path("id") id: Int
    ): Deferred<Game>

    @GET("/api/game/{id}/band")
    fun getGameBand(
            @Path("id") gameId: Int,
            @Query("type") imageType: String?
    ): Deferred<GameBand>

    @GET("/api/comments")
    fun getCommentList(
            @Query("id") ids: List<Int>?,
            @Query("game") gameIds: List<Int>?,
            @QueryMap queryMap: Map<String, String>
    ): Deferred<Container<Comment>>
}