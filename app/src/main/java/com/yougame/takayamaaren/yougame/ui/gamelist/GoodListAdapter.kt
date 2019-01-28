package com.yougame.takayamaaren.yougame.ui.gamelist

import android.view.View
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.yougame.takayamaaren.yougame.R

class GoodListAdapter(val datas : MutableList<GoodListItem>) : BaseQuickAdapter<GoodListItem, GoodViewHolder>(R.layout.card_game_medium,datas) {
    override fun convert(helper: GoodViewHolder?, item: GoodListItem?) {

    }
}

class GoodListItem

class GoodViewHolder(itemView : View) : BaseViewHolder(itemView)