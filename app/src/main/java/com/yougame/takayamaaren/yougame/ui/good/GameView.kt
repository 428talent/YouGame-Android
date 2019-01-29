package com.yougame.takayamaaren.yougame.ui.good

import com.yougame.takayamaaren.yougame.sdk.model.response.Game
import com.yougame.takayamaaren.yougame.sdk.model.response.Good
import com.yougame.takayamaaren.yougame.ui.base.View
import com.yougame.takayamaaren.yougame.ui.good.components.comment.CommentItem

interface GameView : View {
    fun onGameLoad(game: Game)
    fun onGameCoverLoad(url: String)
    fun onGoodsLoad(good: List<Good>)
    fun onCommentLoad(comment : List<CommentItem>)
}