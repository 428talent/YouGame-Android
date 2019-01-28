package com.yougame.takayamaaren.yougame.ui.main.ranking

import android.view.View
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.yougame.takayamaaren.yougame.R

class RankingAdapter(val datas : MutableList<RankingItem>) : BaseQuickAdapter<RankingItem, RankingViewHolder>(R.layout.card_game,datas) {
    override fun convert(helper: RankingViewHolder?, item: RankingItem?) {

    }
}

class RankingItem

class RankingViewHolder(itemView : View) : BaseViewHolder(itemView)