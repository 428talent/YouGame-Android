package com.yougame.takayamaaren.yougame.services.cart

import com.yougame.takayamaaren.yougame.manager.user.UserManager
import com.yougame.takayamaaren.yougame.sdk.model.response.CartItem
import com.yougame.takayamaaren.yougame.sdk.model.response.Container
import com.yougame.takayamaaren.yougame.services.base.BaseQueryBuilder
import com.yougame.takayamaaren.yougame.ui.clients.ApiClient

class CartQueryBuilder : BaseQueryBuilder<CartQueryBuilder>() {
    private val goodIds = mutableListOf<Int>()
    fun inGoodId(vararg goodIds: Int): CartQueryBuilder {
        this.goodIds.addAll(goodIds.toList())
        return this
    }

    fun inGoodId(goodIds: List<Int>): CartQueryBuilder {
        this.goodIds.addAll(goodIds)
        return this
    }

    override fun getThis(): CartQueryBuilder {
        return this
    }

    suspend fun query(): Container<CartItem> {
        UserManager.user?.let {
            return ApiClient.client.getCartList(it.sign, goodIds, mapOf(
                    "page" to page.toString(),
                    "pageSize" to pageSize.toString()
            )).await()
        }
        throw IllegalAccessError("user not login")

    }
}