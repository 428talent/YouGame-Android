package com.yougame.takayamaaren.yougame.main.hot.components.gameset

import android.view.View
import com.bumptech.glide.Glide
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.yougame.takayamaaren.yougame.R
import kotlinx.android.synthetic.main.item_game_set_large.view.*

class GameSetAdapter(val datas: MutableList<GameSetItem>) : BaseQuickAdapter<GameSetItem, GameSetViewHolder>(R.layout.item_game_set_large, datas) {
    override fun convert(helper: GameSetViewHolder, item: GameSetItem) {
        helper.itemView.game_name.text = item.gameName
        helper.itemView.meta.text = item.meta
        Glide.with(helper.itemView).load(item.cover).into(helper.itemView.cover)

    }
}

class GameSetItem(val gameName: String, val meta: String, val cover: String)


class GameSetViewHolder(itemView: View) : BaseViewHolder(itemView)