package com.yougame.takayamaaren.yougame.sdk.model.response

import com.google.gson.annotations.SerializedName


data class Container<T>(
        val count: Int,
        @SerializedName("next_page")
        val nextPage: String?,
        val page: Int,
        @SerializedName("page_size")
        val pageSize: Int,
        @SerializedName("prev_page")
        val prevPage: String?,
        val result: List<T>
)