package com.yougame.takayamaaren.yougame.ui.good.comments

import com.chad.library.adapter.base.BaseQuickAdapter
import com.yougame.takayamaaren.yougame.services.comment.CommentQueryBuilder
import com.yougame.takayamaaren.yougame.ui.base.Presenter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

interface CommentPresenter : Presenter<CommentsView> {
    fun loadComments()
    fun initPresenter(gameId: Int)
    fun loadMore()
}

class CommentPresenterImpl : CommentPresenter {
    lateinit var view: CommentsView
    private var gameId: Int = 0
    override fun onAttach(view: CommentsView) {
        this.view = view
    }

    private var page = 1

    private var hasMore = true

    override fun onDetach() {

    }


    override fun initPresenter(gameId: Int) {
        this.gameId = gameId
    }

    override fun loadMore() {
        if (!hasMore) {
            return
        }
        page += 1
        GlobalScope.launch(Dispatchers.IO) {
            val commentsResponse = CommentQueryBuilder().inGame(gameId).inPage(page, 10).query()
            hasMore = !commentsResponse.nextPage.isNullOrEmpty()
            launch(Dispatchers.Main) {
                view.addComments(commentsResponse.result)
            }
            if (!hasMore) {
                launch(Dispatchers.Main) {
                    view.onLoadDone()
                }
            }
        }
    }

    override fun loadComments() {
        GlobalScope.launch(Dispatchers.IO) {
            val commentsResponse = CommentQueryBuilder().inGame(gameId).inPage(page, 10).query()
            if (commentsResponse.nextPage.isNullOrEmpty()) {
                hasMore = true
            }
            launch(Dispatchers.Main) {
                view.setComments(commentsResponse.result)
            }

        }
    }

}