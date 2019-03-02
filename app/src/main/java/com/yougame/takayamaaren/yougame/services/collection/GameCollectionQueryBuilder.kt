package com.yougame.takayamaaren.yougame.services.collection

import com.yougame.takayamaaren.yougame.sdk.model.response.Container
import com.yougame.takayamaaren.yougame.sdk.model.response.Game
import com.yougame.takayamaaren.yougame.sdk.model.response.GameCollection
import com.yougame.takayamaaren.yougame.services.base.BaseQueryBuilder
import com.yougame.takayamaaren.yougame.ui.clients.ApiClient

class GameCollectionQueryBuilder : BaseQueryBuilder<GameCollectionQueryBuilder>() {
    private val names: MutableList<String> = mutableListOf()
    override fun getThis(): GameCollectionQueryBuilder {
        return this
    }

    fun withName(vararg names: String): GameCollectionQueryBuilder {
        this.names.addAll(names)
        return this
    }

    fun withName(name: String): GameCollectionQueryBuilder {
        this.names.add(name)
        return this
    }

    suspend fun query(): Container<GameCollection> {
        return ApiClient.client.getGameCollection(names,
                mapOf(
                        "page" to page.toString(),
                        "pageSize" to pageSize.toString()
                )).await()
    }
}