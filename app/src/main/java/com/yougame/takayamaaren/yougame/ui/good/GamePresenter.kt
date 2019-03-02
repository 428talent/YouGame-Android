package com.yougame.takayamaaren.yougame.ui.good

import com.yougame.takayamaaren.yougame.sdk.model.response.CartItem
import com.yougame.takayamaaren.yougame.sdk.model.response.Good
import com.yougame.takayamaaren.yougame.sdk.model.response.InventoryItem
import com.yougame.takayamaaren.yougame.services.cart.CartQueryBuilder
import com.yougame.takayamaaren.yougame.services.cart.CartServices
import com.yougame.takayamaaren.yougame.services.comment.CommentQueryBuilder
import com.yougame.takayamaaren.yougame.services.game.GameServices
import com.yougame.takayamaaren.yougame.services.good.GoodQueryBuilder
import com.yougame.takayamaaren.yougame.services.good.GoodServices
import com.yougame.takayamaaren.yougame.services.inventory.InventoryItemQueryBuilder
import com.yougame.takayamaaren.yougame.services.user.ProfileQueryBuilder
import com.yougame.takayamaaren.yougame.ui.base.Presenter
import com.yougame.takayamaaren.yougame.ui.clients.ApiClient
import com.yougame.takayamaaren.yougame.ui.good.components.comment.CommentItem
import com.yougame.takayamaaren.yougame.ui.good.components.good.GoodItem
import com.yougame.takayamaaren.yougame.utils.AppConfig
import com.yougame.takayamaaren.yougame.utils.Rating
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

interface GamePresenter : Presenter<GameView> {
    fun loadGame(gameId: Int)
    fun loadGoods(gameId: Int)
    fun loadComments(gameId: Int)
    fun addToCart(goodIds: List<Int>)
}

class GamePresenterImpl : GamePresenter {
    private lateinit var view: GameView
    override fun onAttach(view: GameView) {
        this.view = view
    }

    override fun onDetach() {

    }

    override fun loadGame(gameId: Int) {
        GlobalScope.launch(Dispatchers.IO) {
            val game = GameServices.getGame(gameId)
            val gameBand = GameServices.fetchGameBand(gameId)
            launch(Dispatchers.Main) {
                view.onGameLoad(game)
                view.onGameCoverLoad("${AppConfig.ApiServerUrl}${gameBand.path}")
            }
        }

    }

    override fun loadGoods(gameId: Int) {
        GlobalScope.launch(Dispatchers.IO) {
            val goods = GoodQueryBuilder().inGame(gameId).query()
            // check good
            val goodIds = goods.result.map { good: Good -> good.id }.distinct()
            val cartItems = CartQueryBuilder().inGoodId(goodIds).inPage(1, goods.result.count()).query()
            val inventoryItem = InventoryItemQueryBuilder().inGoodId(goodIds).query()
            launch(Dispatchers.Main) {
                view.onGoodsLoad(goods.result.map { good: Good ->
                    GoodItem(good, cartItems.result.any { item -> item.goodId == good.id }, inventoryItem.result.any { item -> item.goodId == good.id })
                })
            }
        }
    }

    override fun loadComments(gameId: Int) {
        loadGameSummary(gameId)
        GlobalScope.launch(Dispatchers.IO) {
            val comments = CommentQueryBuilder().inGame(gameId).query()
            val profile = ProfileQueryBuilder().inUser(comments.result.map { comment -> comment.userId }.distinct()).query()
            val goods = GoodQueryBuilder().inId(comments.result.map { comment -> comment.goodId }.distinct()).query()
            launch(Dispatchers.Main) {
                view.onCommentLoad(comments.result.map { comment ->
                    CommentItem(
                            content = comment.content,
                            rating = comment.rating,
                            avatarUrl = "${AppConfig.ApiServerUrl}${profile.result.find { profile -> profile.userId == comment.userId }?.avatar}",
                            name = profile.result.find { profile -> profile.userId == comment.userId }?.nickname
                                    ?: "",
                            goodName = goods.result.find { good -> comment.goodId == good.id }?.name
                                    ?: ""
                    )
                })
            }
        }
    }

    private fun loadGameSummary(gameId: Int) {
        GlobalScope.launch(Dispatchers.IO) {
            val commentSummary = GameServices.getGameCommentSummary(gameId)
            val total = commentSummary.ratingCount.map { it.rating * it.count }.sum()
            if (total == 0) {
                view.setCommentSummary("无数据", "无数据")
            }
            val avg = total.toDouble() / commentSummary.ratingCount.map { it.count }.sum()
            launch(Dispatchers.Main) {
                view.setCommentSummary(Rating.GetRatingText(avg), String.format("%.1f",avg))
            }

        }
    }

    override fun addToCart(goodIds: List<Int>) {
        GlobalScope.launch(Dispatchers.IO) {
            val task = mutableMapOf<Int, CartItem>()
            goodIds.forEach { goodId ->
                task[goodId] = CartServices.addToCart(goodId)
            }

            launch(Dispatchers.Main) {
                view.onAddCartComplete(task.map { it.value.goodId })
            }
        }
    }


}