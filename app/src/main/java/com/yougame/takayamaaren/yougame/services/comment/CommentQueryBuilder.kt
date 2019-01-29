package com.yougame.takayamaaren.yougame.services.comment

import com.yougame.takayamaaren.yougame.sdk.model.response.Comment
import com.yougame.takayamaaren.yougame.sdk.model.response.Container
import com.yougame.takayamaaren.yougame.services.base.BaseQueryBuilder
import com.yougame.takayamaaren.yougame.ui.clients.ApiClient

class CommentQueryBuilder : BaseQueryBuilder<CommentQueryBuilder>() {
    private val gameIds: MutableList<Int> = mutableListOf()
    override fun getThis(): CommentQueryBuilder {
        return this
    }

    fun inGame(vararg gameIds: Int): CommentQueryBuilder {
        this.gameIds.addAll(gameIds.toList())
        return this
    }

    suspend fun query(): Container<Comment> {
        return ApiClient.client.getCommentList(ids, gameIds, mapOf(
                "page" to page.toString(),
                "pageSize" to pageSize.toString()
        )).await()
    }
}