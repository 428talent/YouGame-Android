package com.yougame.takayamaaren.yougame.ui.good.comments

import com.yougame.takayamaaren.yougame.services.comment.CommentQueryBuilder
import com.yougame.takayamaaren.yougame.ui.base.Presenter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

interface CommentPresenter : Presenter<CommentsView> {
    fun loadComments()
    fun initPresenter(gameId : Int)
}

class CommentPresenterImpl : CommentPresenter{
    lateinit var view : CommentsView
    private var gameId : Int = 0
    override fun onAttach(view: CommentsView) {
        this.view = view
    }

    override fun onDetach() {

    }


    override fun initPresenter(gameId: Int) {
        this.gameId = gameId
    }

    override fun loadComments() {
        GlobalScope.launch(Dispatchers.IO) {
            val commentsResponse = CommentQueryBuilder().inGame(gameId).query()
            launch(Dispatchers.Main){
                view.setComments(commentsResponse.result)
            }

        }
    }

}