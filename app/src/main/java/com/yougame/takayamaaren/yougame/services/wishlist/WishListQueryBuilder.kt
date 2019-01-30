package com.yougame.takayamaaren.yougame.services.wishlist

import com.yougame.takayamaaren.yougame.manager.user.UserManager
import com.yougame.takayamaaren.yougame.sdk.model.response.Container
import com.yougame.takayamaaren.yougame.sdk.model.response.WishListItem
import com.yougame.takayamaaren.yougame.services.base.BaseQueryBuilder
import com.yougame.takayamaaren.yougame.ui.clients.ApiClient

class WishListQueryBuilder : BaseQueryBuilder<WishListQueryBuilder>() {
    override fun getThis(): WishListQueryBuilder {
        return this
    }

    suspend fun query(): Container<WishListItem> {
        val params = mutableMapOf<String, String>().apply {
            put("page", page.toString())
            put("pageSize", pageSize.toString())
        }
        UserManager.user?.let {
            return ApiClient.client.getWishListItems(it.sign, params).await()
        }

        throw IllegalAccessError("user not login")

    }
}