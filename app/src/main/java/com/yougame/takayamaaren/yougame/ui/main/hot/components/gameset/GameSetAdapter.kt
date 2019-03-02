package com.yougame.takayamaaren.yougame.ui.main.hot.components.gameset

import android.view.View
import com.bumptech.glide.Glide
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.yougame.takayamaaren.yougame.R
import com.yougame.takayamaaren.yougame.services.game.GameServices
import com.yougame.takayamaaren.yougame.utils.AppConfig
import kotlinx.android.synthetic.main.item_game_set_large.view.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class GameSetAdapter(val datas: MutableList<GameSetItem>) : BaseQuickAdapter<GameSetItem, GameSetViewHolder>(R.layout.item_game_set_large, datas) {
    override fun convert(helper: GameSetViewHolder, item: GameSetItem) {
        helper.itemView.game_name.text = item.gameName
        helper.itemView.meta.text = item.meta
        GlobalScope.launch(Dispatchers.IO) {
            val response = GameServices.fetchGameBand(item.gameId)
            launch(Dispatchers.Main) {
                Glide.with(helper.itemView).load("${AppConfig.ApiServerUrl}${response.path}").into(helper.itemView.cover)
            }
        }


    }
}

class GameSetItem(val gameName: String, val meta: String, val gameId: Int)


class GameSetViewHolder(itemView: View) : BaseViewHolder(itemView)