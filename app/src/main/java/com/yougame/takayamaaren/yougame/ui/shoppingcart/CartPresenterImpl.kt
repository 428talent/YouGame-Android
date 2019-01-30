package com.yougame.takayamaaren.yougame.ui.shoppingcart

import com.yougame.takayamaaren.yougame.sdk.ApiError
import com.yougame.takayamaaren.yougame.sdk.model.response.GameBand
import com.yougame.takayamaaren.yougame.services.cart.CartServices
import com.yougame.takayamaaren.yougame.services.game.GameQueryBuilder
import com.yougame.takayamaaren.yougame.services.game.GameServices
import com.yougame.takayamaaren.yougame.services.good.GoodQueryBuilder
import com.yougame.takayamaaren.yougame.utils.AppConfig
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class CartPresenterImpl : CartPresenter {
    private lateinit var view: CartView
    override fun onAttach(view: CartView) {
        this.view = view
    }

    override fun getWishlistItems() {
        GlobalScope.launch(Dispatchers.IO) {
            val cartItemList = CartServices.getCartList(page = 1, pageSize = 10)
            val goodIds = cartItemList.result.map { item -> item.goodId }.distinct()
            val goodListResponse = GoodQueryBuilder().inId(goodIds).inPage(1, goodIds.size).query()
            val gameIds = goodListResponse.result.map { good -> good.gameId }.distinct()
            val gameListResponse = GameQueryBuilder().inId(gameIds).inPage(1, gameIds.size).query()
            val gameBands = mutableMapOf<Int, GameBand>()
            gameListResponse.result.forEach { game ->
                gameBands[game.id] = GameServices.fetchGameBand(game.id,imageType = "desktop")
            }
            launch(Dispatchers.Main) {
                view.onLoadCartItemComplete(cartItemList.result.map { item ->
                    ShoppingCartItem(
                            itemId = item.id,
                            name = goodListResponse.result.first { good -> good.id == item.goodId }.name,
                            price = goodListResponse.result.first { good -> good.id == item.goodId }.price.toString(),
                            coverUrl = goodListResponse.result.first { good -> good.id == item.goodId }.gameId.let {
                                "${AppConfig.ApiServerUrl}${gameBands[it]?.path ?: ""}"
                            },
                            gameId = goodListResponse.result.first { good -> good.id == item.goodId }.gameId

                    )
                })
            }
        }
    }

    override fun deleteItem(ids: List<Int>) {
        val task = mutableMapOf<Int, Boolean>()
        GlobalScope.launch(Dispatchers.IO) {
            ids.forEach {
                try {
                    val result = CartServices.deleteCartItem(it)
                    task[it] = result.success
                } catch (e: ApiError) {
                    task[it] = false
                    e.printStackTrace()
                }

            }

            launch(Dispatchers.Main) {
                view.removeCartItems(task.filter { it.value }.map { it.key })
            }
        }

    }

    override fun onDetach() {

    }
}