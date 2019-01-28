package com.yougame.takayamaaren.yougame.services.game

import com.yougame.takayamaaren.yougame.sdk.model.response.Container
import com.yougame.takayamaaren.yougame.sdk.model.response.Game
import com.yougame.takayamaaren.yougame.services.base.BaseQueryBuilder
import com.yougame.takayamaaren.yougame.ui.clients.ApiClient

public class GameQueryBuilder : BaseQueryBuilder<GameQueryBuilder>() {
    override fun getThis(): GameQueryBuilder {
        return this
    }


    suspend fun query(): Container<Game> {
        return ApiClient.client.getGameList(ids, mapOf(
                "page" to page.toString(),
                "pageSize" to pageSize.toString()
        )).await()
    }
}