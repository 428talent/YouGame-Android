package com.yougame.takayamaaren.yougame.ui.good.comments

import com.yougame.takayamaaren.yougame.sdk.model.response.Comment
import com.yougame.takayamaaren.yougame.ui.base.View

interface CommentsView : View {
    fun setComments(list: List<Comment>)
}