package com.yougame.takayamaaren.yougame.ui.category

import android.app.Activity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.LinearSnapHelper
import android.view.View
import android.widget.LinearLayout
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.yougame.takayamaaren.yougame.Game
import com.yougame.takayamaaren.yougame.R
import com.yougame.takayamaaren.yougame.ui.good.GoodActivity
import com.yougame.takayamaaren.yougame.ui.main.hot.components.gameset.GameSetAdapter
import com.yougame.takayamaaren.yougame.ui.main.hot.components.gameset.GameSetItem
import kotlinx.android.synthetic.main.card_game_set_large.view.*
import org.jetbrains.anko.startActivity

class CategoryAdapter(val datas: MutableList<CategoryItem>,val activity: Activity) : BaseQuickAdapter<CategoryItem, CategoryViewHolder>(R.layout.card_game_set_large, datas) {
    override fun convert(helper: CategoryViewHolder, item: CategoryItem) {
        with(item, {
            helper.itemView.game_set_title.text = item.title
            val layoutManager = LinearLayoutManager(helper.itemView.context)
            layoutManager.orientation = LinearLayout.HORIZONTAL

            helper.itemView.game_set_rv.layoutManager = layoutManager
            val snapHelper = LinearSnapHelper()
            takeIf { isFirst }?.let {
                snapHelper.attachToRecyclerView(helper.itemView.game_set_rv)
                isFirst = false
            }
            val adapter = GameSetAdapter(games.map {
                GameSetItem(it.name, it.meta, it.cover)
            }.toMutableList()).apply {
                setOnItemClickListener { adapter, view, position ->
                    activity.startActivity<GoodActivity>("Game" to data[position])
                }
            }
            helper.itemView.game_set_rv.adapter = adapter
        })
    }
}

class CategoryItem(val games : List<Game>,val title : String){
    var isFirst = true
}

class CategoryViewHolder(itemView: View) : BaseViewHolder(itemView)