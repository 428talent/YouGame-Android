package com.yougame.takayamaaren.yougame.services.order

import com.yougame.takayamaaren.yougame.manager.user.UserManager
import com.yougame.takayamaaren.yougame.sdk.model.response.Container
import com.yougame.takayamaaren.yougame.sdk.model.response.Order
import com.yougame.takayamaaren.yougame.services.base.BaseQueryBuilder
import com.yougame.takayamaaren.yougame.ui.clients.ApiClient

class OrderQueryBuilder : BaseQueryBuilder<OrderQueryBuilder>() {
    override fun getThis(): OrderQueryBuilder {
        return this
    }

    suspend fun query(): Container<Order> {
        UserManager.user?.let {
            return ApiClient.client.getOrderList(it.sign, mapOf(
                    "page" to page.toString(),
                    "pageSize" to pageSize.toString()
            )).await()
        }
        throw IllegalAccessError("user not login")
    }
}