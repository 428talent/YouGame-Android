package com.yougame.takayamaaren.yougame.services.cart

import com.yougame.takayamaaren.yougame.manager.user.UserManager
import com.yougame.takayamaaren.yougame.sdk.model.request.AddCartRequestBody
import com.yougame.takayamaaren.yougame.sdk.model.response.CartItem
import com.yougame.takayamaaren.yougame.sdk.model.response.Common
import com.yougame.takayamaaren.yougame.sdk.model.response.Container
import com.yougame.takayamaaren.yougame.ui.clients.ApiClient

object CartServices {
    suspend fun deleteCartItem(id: Int): Common {
        UserManager.user?.let {
            return ApiClient.client.deleteCartItem(it.sign, id).await()
        }
        throw IllegalAccessError("user not login")
    }

    suspend fun addToCart(goodId: Int): CartItem {
        UserManager.user?.let {
            return ApiClient.client.addToCart(it.sign, AddCartRequestBody(goodId)).await()
        }
        throw IllegalAccessError("user not login")
    }
}