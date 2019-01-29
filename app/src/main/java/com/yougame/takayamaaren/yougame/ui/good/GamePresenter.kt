package com.yougame.takayamaaren.yougame.ui.good

import com.yougame.takayamaaren.yougame.services.comment.CommentQueryBuilder
import com.yougame.takayamaaren.yougame.services.game.GameServices
import com.yougame.takayamaaren.yougame.services.good.GoodQueryBuilder
import com.yougame.takayamaaren.yougame.services.good.GoodServices
import com.yougame.takayamaaren.yougame.services.user.ProfileQueryBuilder
import com.yougame.takayamaaren.yougame.ui.base.Presenter
import com.yougame.takayamaaren.yougame.ui.clients.ApiClient
import com.yougame.takayamaaren.yougame.ui.good.components.comment.CommentItem
import com.yougame.takayamaaren.yougame.utils.AppConfig
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

interface GamePresenter : Presenter<GameView> {
    fun loadGame(gameId: Int)
    fun loadGoods(gameId: Int)
    fun loadComments(gameId: Int)
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
            launch(Dispatchers.Main) {
                view.onGoodsLoad(goods.result)
            }
        }
    }

    override fun loadComments(gameId: Int) {
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


}