package com.yougame.takayamaaren.yougame.sdk

import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class ApiService {
    companion object {
        private const val apiServerUrl = "http://119.23.208.32:8000/"
        private const val apiDevelopment = "http://192.168.31.107:8888/"
        private const val baseUrl = apiDevelopment
        private val gson = GsonBuilder()
                .create()
        val api: ApiInterface by lazy { createClient() }
        private fun createClient(): ApiInterface {
            val client = Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .addCallAdapterFactory(CoroutineCallAdapterFactory())
                    .client(
                            OkHttpClient.Builder()
                                    .readTimeout(15, TimeUnit.SECONDS)
                                    .writeTimeout(15, TimeUnit.SECONDS)
                                    .build()
                    )
                    .build()
            return client.create(ApiInterface::class.java)
        }
    }
}