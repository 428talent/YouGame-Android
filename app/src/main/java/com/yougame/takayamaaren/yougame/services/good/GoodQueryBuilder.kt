package com.yougame.takayamaaren.yougame.services.good

import com.yougame.takayamaaren.yougame.sdk.model.response.Container
import com.yougame.takayamaaren.yougame.sdk.model.response.Game
import com.yougame.takayamaaren.yougame.sdk.model.response.Good
import com.yougame.takayamaaren.yougame.services.base.BaseQueryBuilder
import com.yougame.takayamaaren.yougame.ui.clients.ApiClient

class GoodQueryBuilder : BaseQueryBuilder<GoodQueryBuilder>() {
    private val gameIds: MutableList<Int> = mutableListOf()
    override fun getThis(): GoodQueryBuilder {
        return this
    }

    fun inGame(vararg gameIds: Int): GoodQueryBuilder {
        this.gameIds.addAll(gameIds.toList())
        return this
    }

    suspend fun query(): Container<Good> {
        return ApiClient.client.getGoodList(
                ids,
                gameIds,
                mapOf(
                        "page" to page.toString(),
                        "pageSize" to pageSize.toString()
                )).await()
    }
}