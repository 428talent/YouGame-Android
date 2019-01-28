package com.yougame.takayamaaren.yougame.services

import com.yougame.takayamaaren.yougame.manager.user.UserManager
import com.yougame.takayamaaren.yougame.sdk.model.response.CartItem
import com.yougame.takayamaaren.yougame.sdk.model.response.Container
import com.yougame.takayamaaren.yougame.ui.clients.ApiClient

object CartServices {
    suspend fun getCartList(
            page: Int = 1,
            pageSize: Int = 10,
            vararg queryOption: Pair<String, String>
    ): Container<CartItem> {

        val params = mutableMapOf<String, String>().apply {
            putAll(queryOption)
            put("page", page.toString())
            put("pageSize", pageSize.toString())
        }
        UserManager.user?.let {
            return ApiClient.client.getCartList(it.sign, params).await()
        }

        throw IllegalAccessError("user not login")

    }
}