package com.yougame.takayamaaren.yougame.main.hot

import android.view.View
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.yougame.takayamaaren.yougame.R

class HotSaleAdapter(val datas : MutableList<HotSaleItem>) : BaseQuickAdapter<HotSaleItem, HotSaleViewHolder>(R.layout.card_recommend,datas) {
    override fun convert(helper: HotSaleViewHolder?, item: HotSaleItem) {

    }
}

class HotSaleItem


class HotSaleViewHolder(itemView : View) : BaseViewHolder(itemView)