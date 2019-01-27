package com.yougame.takayamaaren.yougame.main.hot.components.gametype

import android.view.View
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.yougame.takayamaaren.yougame.R
import kotlinx.android.synthetic.main.item_game_type.view.*

class GameTypeAdapter(val datas: MutableList<GameTypeItem>) : BaseQuickAdapter<GameTypeItem, GameTypeViewHolder>(R.layout.item_game_type, datas) {
    override fun convert(helper: GameTypeViewHolder, item: GameTypeItem) {
        helper.itemView.type_icon.setImageResource(item.icon)
        helper.itemView.type_name.text = item.name
    }
}

class GameTypeItem(val name: String, val icon: Int)

class GameTypeViewHolder(itemView: View) : BaseViewHolder(itemView)