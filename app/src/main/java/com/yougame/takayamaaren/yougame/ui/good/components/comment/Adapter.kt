package com.yougame.takayamaaren.yougame.ui.good.components.comment

import com.bumptech.glide.Glide
import com.chad.library.adapter.base.BaseQuickAdapter
import com.yougame.takayamaaren.yougame.R
import com.yougame.takayamaaren.yougame.ui.base.SimpleViewHolder
import kotlinx.android.synthetic.main.item_good_comment.view.*

class Adapter(comments: MutableList<CommentItem>) : BaseQuickAdapter<CommentItem, SimpleViewHolder>(R.layout.item_good_comment) {
    override fun convert(helper: SimpleViewHolder, item: CommentItem) {
        with(helper.itemView) {
            tv_content.text = item.content
            tv_name.text = item.name
            tv_rating.text = item.rating.toString()
            cp_good_name.chipText = item.goodName
            Glide.with(this).load(item.avatarUrl).into(iv_avatar)
        }
    }
}

class CommentItem(val content: String, val name: String, val rating: Int, val avatarUrl: String, val goodName: String)