package com.yougame.takayamaaren.yougame.services.inventory

import com.yougame.takayamaaren.yougame.manager.user.UserManager
import com.yougame.takayamaaren.yougame.sdk.model.response.Container
import com.yougame.takayamaaren.yougame.sdk.model.response.InventoryItem
import com.yougame.takayamaaren.yougame.services.base.BaseQueryBuilder
import com.yougame.takayamaaren.yougame.ui.clients.ApiClient

class InventoryItemQueryBuilder : BaseQueryBuilder<InventoryItemQueryBuilder>() {
    override fun getThis(): InventoryItemQueryBuilder {
        return this
    }

    private val goodIds = mutableListOf<Int>()
    fun inGoodId(vararg goodIds: Int): InventoryItemQueryBuilder {
        this.goodIds.addAll(goodIds.toList())
        return this
    }

    fun inGoodId(goodIds: List<Int>): InventoryItemQueryBuilder {
        this.goodIds.addAll(goodIds)
        return this
    }

    suspend fun query(): Container<InventoryItem> {
        UserManager.user?.let {
            return ApiClient.client.getInventoryItemList(it.sign, goodIds, mapOf(
                    "page" to page.toString(),
                    "pageSize" to pageSize.toString()
            )).await()
        }
        throw IllegalAccessError("user not login")

    }
}