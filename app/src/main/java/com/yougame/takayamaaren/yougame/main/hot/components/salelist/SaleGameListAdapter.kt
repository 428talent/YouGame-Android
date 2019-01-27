package com.yougame.takayamaaren.yougame.main.hot.components.salelist

import android.view.View
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.yougame.takayamaaren.yougame.R
import kotlinx.android.synthetic.main.item_game_sale.view.*

class SaleGameAdapter(val datas: MutableList<SaleGameItem>) : BaseQuickAdapter<SaleGameItem, SaleGameViewHolder>(R.layout.item_game_sale, datas) {
    override fun convert(helper: SaleGameViewHolder, item: SaleGameItem) {
        helper.itemView.game_list_item_game_name.text = item.gameName
        helper.itemView.game_list_item_game_meta.text = item.meta
        helper.itemView.game_list_item_price.text = item.price.toString()
    }
}

class SaleGameItem(val gameName: String, val price: Float, val meta: String)


class SaleGameViewHolder(itemView: View) : BaseViewHolder(itemView)