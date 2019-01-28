package com.yougame.takayamaaren.yougame.ui.friends

import android.view.View
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.yougame.takayamaaren.yougame.R
import kotlinx.android.synthetic.main.item_friends.view.*

class FriendAdapter(val datas: MutableList<FriendItem>) : BaseQuickAdapter<FriendItem, FriendViewHolder>(R.layout.item_friends, datas) {
    override fun convert(helper: FriendViewHolder, item: FriendItem) {
        helper.itemView.nickname.text = item.nickname
    }
}

class FriendItem(val avatar: String, val nickname: String)

class FriendViewHolder(itemView: View) : BaseViewHolder(itemView)