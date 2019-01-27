package com.yougame.takayamaaren.yougame.account.fragments.feeds

import android.view.View
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.yougame.takayamaaren.yougame.R

class AccountFeedsAdapter(val datas : MutableList<AccountFeedsItem>) : BaseQuickAdapter<AccountFeedsItem,AccountFeedViewHolder>(R.layout.card_feed_game,datas) {
    override fun convert(helper: AccountFeedViewHolder?, item: AccountFeedsItem?) {

    }
}

class AccountFeedsItem

class AccountFeedViewHolder(itemView : View) : BaseViewHolder(itemView)