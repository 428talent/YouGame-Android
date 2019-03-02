package com.yougame.takayamaaren.yougame.ui.good.comments

import com.chad.library.adapter.base.BaseQuickAdapter
import com.yougame.takayamaaren.yougame.R
import com.yougame.takayamaaren.yougame.sdk.model.response.Comment
import com.yougame.takayamaaren.yougame.services.user.ProfileQueryBuilder
import com.yougame.takayamaaren.yougame.ui.base.SimpleViewHolder
import com.yougame.takayamaaren.yougame.utils.AppConfig
import kotlinx.android.synthetic.main.item_game_comment.view.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class CommentsAdapter(comments: MutableList<CommentItem>) : BaseQuickAdapter<CommentItem, SimpleViewHolder>(R.layout.item_game_comment, comments) {
    override fun convert(helper: SimpleViewHolder, item: CommentItem) {
        with(item.comment) {
            helper.itemView.comment.setComment(content, rating, createAt)

        }
        GlobalScope.launch(Dispatchers.IO) {
            val data = ProfileQueryBuilder().inUser(item.comment.userId).query()
            if (data.count == 1) {
                launch(Dispatchers.Main) {
                    val profile = data.result.first()
                    helper.itemView.comment.setAvatar("${AppConfig.ApiServerUrl}${profile.avatar}")
                    helper.itemView.comment.setNickname(profile.nickname)

                }
            }
        }

    }
}

data class CommentItem(val comment: Comment)