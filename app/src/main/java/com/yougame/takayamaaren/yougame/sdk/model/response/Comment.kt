package com.yougame.takayamaaren.yougame.sdk.model.response

import com.google.gson.annotations.SerializedName
import java.util.*


data class Comment(
        val content: String,
        @SerializedName("create_at")
        val createAt: Date,
        @SerializedName("good_id")
        val goodId: Int,
        val id: Int,
        val rating: Int,
        @SerializedName("update_at")
        val updateAt: Date,
        @SerializedName("user_id")
        val userId: Int
)

data class CommentSummary(
    @SerializedName("rating_count")
    val ratingCount: List<RatingCount>
) {
    data class RatingCount(
        val count: Int,
        val rating: Int
    )
}