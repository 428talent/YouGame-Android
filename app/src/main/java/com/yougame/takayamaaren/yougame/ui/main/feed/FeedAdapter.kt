package com.yougame.takayamaaren.yougame.ui.main.feed

import android.view.View
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.chad.library.adapter.base.entity.MultiItemEntity
import com.yougame.takayamaaren.yougame.R

class FeedAdapter(val feeds: List<FeedMultiItem>) : BaseMultiItemQuickAdapter<FeedMultiItem, FeedViewHolder>(feeds) {
    init {
        addItemType(FeedMultiItem.VIEW_TYPE_GAME, R.layout.card_feed_game)
        addItemType(FeedMultiItem.VIEW_TYPE_COMMENT, R.layout.card_feed_comment)
    }

    override fun convert(helper: FeedViewHolder?, item: FeedMultiItem?) {

    }

}


class FeedItem

class FeedViewHolder(itemView: View) : BaseViewHolder(itemView)
class FeedMultiItem(val viewType: Int) : MultiItemEntity {
    override fun getItemType(): Int {
        return viewType
    }

    companion object {
        const val VIEW_TYPE_GAME = 0x001
        const val VIEW_TYPE_COMMENT = 0x002
    }
}