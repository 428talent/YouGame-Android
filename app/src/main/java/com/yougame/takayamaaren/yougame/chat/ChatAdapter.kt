package com.yougame.takayamaaren.yougame.chat

import android.view.View
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.chad.library.adapter.base.entity.MultiItemEntity
import com.yougame.takayamaaren.yougame.R

class ChatAdapter(val messages: List<ChatMultiItem>) : BaseMultiItemQuickAdapter<ChatMultiItem, ChatViewHolder>(messages) {
    init {
        addItemType(ChatMultiItem.VIEW_TYPE_MESSAGE, R.layout.item_chat_bubble)
        addItemType(ChatMultiItem.VIEW_TYPE_SELF_MESSAGE, R.layout.item_chat_self_bubble)
    }

    override fun convert(helper: ChatViewHolder?, item: ChatMultiItem?) {

    }

}


class ChatItem

class ChatViewHolder(itemView: View) : BaseViewHolder(itemView)
class ChatMultiItem(val viewType: Int) : MultiItemEntity {
    override fun getItemType(): Int {
        return viewType
    }

    companion object {
        const val VIEW_TYPE_MESSAGE = 0x001
        const val VIEW_TYPE_SELF_MESSAGE = 0x002
    }
}