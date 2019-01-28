package com.yougame.takayamaaren.yougame.services.good

import com.yougame.takayamaaren.yougame.sdk.model.response.Container
import com.yougame.takayamaaren.yougame.sdk.model.response.Game
import com.yougame.takayamaaren.yougame.sdk.model.response.Good
import com.yougame.takayamaaren.yougame.services.base.BaseQueryBuilder
import com.yougame.takayamaaren.yougame.ui.clients.ApiClient

class GoodQueryBuilder : BaseQueryBuilder<GoodQueryBuilder>() {
    override fun getThis(): GoodQueryBuilder {
        return this
    }

    suspend fun query(): Container<Good> {
        return ApiClient.client.getGoodList(ids, mapOf(
                "page" to page.toString(),
                "pageSize" to pageSize.toString()
        )).await()
    }
}