package com.yougame.takayamaaren.yougame.ui.wishlist

import com.yougame.takayamaaren.yougame.sdk.model.response.GameBand
import com.yougame.takayamaaren.yougame.services.game.GameQueryBuilder
import com.yougame.takayamaaren.yougame.services.game.GameServices
import com.yougame.takayamaaren.yougame.services.wishlist.WishListQueryBuilder
import com.yougame.takayamaaren.yougame.ui.base.Presenter
import com.yougame.takayamaaren.yougame.utils.AppConfig
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

interface WishListPresenter : Presenter<WishListView> {
    fun loadWishListItems()
}

class WishListPresenterImpl : WishListPresenter {
    private lateinit var view: WishListView
    override fun onAttach(view: WishListView) {
        this.view = view
    }

    override fun onDetach() {

    }

    override fun loadWishListItems() {
        GlobalScope.launch(Dispatchers.IO) {
            val itemList = WishListQueryBuilder().inPage(1, 10).query()
            val gameIds = itemList.result.map { it.gameId }.distinct()
            val gameList = GameQueryBuilder().inId(gameIds).inPage(1, gameIds.size).query()
            val bands = mutableMapOf<Int, GameBand>()
            gameIds.forEach { gameId ->
                bands[gameId] = GameServices.fetchGameBand(gameId)
            }

            launch(Dispatchers.Main) {
                view.onWishListLoad(itemList.result.map {
                    WishListView.WishListItemModel(it, gameList.result.first { game -> game.id == it.gameId }, "${AppConfig.ApiServerUrl}${bands[it.gameId]?.path
                            ?: ""}")
                })
            }
        }
    }

}