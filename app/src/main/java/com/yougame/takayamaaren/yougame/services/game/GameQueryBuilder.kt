package com.yougame.takayamaaren.yougame.services.game

import com.yougame.takayamaaren.yougame.sdk.model.response.Container
import com.yougame.takayamaaren.yougame.sdk.model.response.Game
import com.yougame.takayamaaren.yougame.services.base.BaseQueryBuilder
import com.yougame.takayamaaren.yougame.ui.clients.ApiClient

public class GameQueryBuilder : BaseQueryBuilder<GameQueryBuilder>() {
    private val collections = mutableListOf<Int>()
    override fun getThis(): GameQueryBuilder {
        return this
    }

    fun withCollection(vararg collections: Int): GameQueryBuilder {
        this.collections.addAll(collections.toList())
        return this
    }

    fun withCollection(collection: Int): GameQueryBuilder {
        this.collections.add(collection)
        return this
    }

    suspend fun query(): Container<Game> {
        return ApiClient.client.getGameList(ids, collections, mapOf(
                "page" to page.toString(),
                "pageSize" to pageSize.toString()
        )).await()
    }
}