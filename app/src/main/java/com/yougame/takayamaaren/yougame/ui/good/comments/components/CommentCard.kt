package com.yougame.takayamaaren.yougame.ui.good.comments.components

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.LinearLayout
import com.bumptech.glide.Glide
import com.yougame.takayamaaren.yougame.R
import kotlinx.android.synthetic.main.card_comments.view.*
import java.util.*

class CommentCard(context: Context, attrs: AttributeSet) : LinearLayout(context, attrs) {
    init {
        View.inflate(context, R.layout.card_comments, this)
    }

    fun setAvatar(avatarUrl: String) {
        if (!avatarUrl.isBlank()) {
            Glide.with(user_avatar).load(avatarUrl).into(user_avatar)
        }
    }

    fun setNickname(nickname: String) {
        user_nickname.text = nickname
    }

    fun setComment(content: String, rating: Int, time: Date) {
        this.rating.text = rating.toString()
        create_time.text = time.toString()
        this.content.text = content
    }
}