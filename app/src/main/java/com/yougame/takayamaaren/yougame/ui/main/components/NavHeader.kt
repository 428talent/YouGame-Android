package com.yougame.takayamaaren.yougame.ui.main.components

import android.content.Context
import android.util.AttributeSet
import android.widget.LinearLayout
import com.bumptech.glide.Glide
import com.yougame.takayamaaren.yougame.R
import kotlinx.android.synthetic.main.nav_header_main.view.*
import org.jetbrains.anko.sdk27.coroutines.onClick

class NavHeader(context: Context, attributeSet: AttributeSet) : LinearLayout(context, attributeSet) {
    private var avatarClick: (() -> Unit)?

    init {
        inflate(context, R.layout.nav_header_main, this)
        avatarClick = {}
        avatar_iv.onClick {
            avatarClick?.invoke()
        }
    }

    fun setAvatar(avatarUrl: String) {
        Glide.with(avatar_iv).load(avatarUrl).into(avatar_iv)
    }

    fun setUsername(username: String) {
        username_tv.text = username
    }

    fun setAvatarClickListener(onAvatarClick: () -> Unit) {
        this.avatarClick = onAvatarClick
    }
}