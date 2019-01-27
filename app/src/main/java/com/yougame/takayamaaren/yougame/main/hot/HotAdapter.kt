package com.yougame.takayamaaren.yougame.main.hot

import android.app.Activity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.LinearSnapHelper
import android.view.View
import android.widget.LinearLayout
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.chad.library.adapter.base.entity.MultiItemEntity
import com.yougame.takayamaaren.yougame.Game
import com.yougame.takayamaaren.yougame.R
import com.yougame.takayamaaren.yougame.category.CategoryActivity
import com.yougame.takayamaaren.yougame.good.GoodActivity
import com.yougame.takayamaaren.yougame.main.hot.components.gameset.GameSetAdapter
import com.yougame.takayamaaren.yougame.main.hot.components.gameset.GameSetItem
import com.yougame.takayamaaren.yougame.main.hot.components.gametype.GameTypeAdapter
import com.yougame.takayamaaren.yougame.main.hot.components.gametype.GameTypeItem
import com.yougame.takayamaaren.yougame.main.hot.components.salelist.SaleGameAdapter
import com.yougame.takayamaaren.yougame.main.hot.components.salelist.SaleGameItem
import kotlinx.android.synthetic.main.card_game.view.*
import kotlinx.android.synthetic.main.card_game_set_large.view.*
import kotlinx.android.synthetic.main.card_game_type_list.view.*
import kotlinx.android.synthetic.main.card_weekly_hot.view.*
import kotlinx.android.synthetic.main.item_hot_sale.view.*
import org.jetbrains.anko.startActivity


class HotAdapter(val items: List<HotMultiItem>, val activity: Activity) : BaseMultiItemQuickAdapter<HotMultiItem, HotViewHolder>(items) {
    init {
        addItemType(HotMultiItem.VIEW_TYPE_HOT_SALE, R.layout.item_hot_sale)
        addItemType(HotMultiItem.VIEW_TYPE_PROMOTIONS, R.layout.card_promotions)
        addItemType(HotMultiItem.VIEW_TYPE_WEEKLY_SALE, R.layout.card_weekly_hot)
        addItemType(HotMultiItem.VIEW_TYPE_RECOMMENDS, R.layout.card_game)
        addItemType(HotMultiItem.VIEW_TYPE_GAME_CATEGORY, R.layout.card_game_type_list)
        addItemType(HotMultiItem.VIEW_TYPE_GAME_SET, R.layout.card_game_set_large)
    }

    override fun convert(helper: HotViewHolder, item: HotMultiItem) {
        when (item.viewType) {
            HotMultiItem.VIEW_TYPE_HOT_SALE -> {
                val layoutManager = LinearLayoutManager(helper.itemView.context)
                layoutManager.orientation = LinearLayout.HORIZONTAL
                helper.itemView.hot_sale_list.layoutManager = layoutManager
                val adapter = HotSaleAdapter(mutableListOf(
                        HotSaleItem(),
                        HotSaleItem(),
                        HotSaleItem(),
                        HotSaleItem()
                ))
                helper.itemView.hot_sale_list.adapter = adapter
            }
            HotMultiItem.VIEW_TYPE_RECOMMENDS -> {
                with(item.data as RecommendGameData, {
                    helper.itemView.game_name.text = gameName
                    helper.itemView.game_price.text = price.toString()
                })
            }
            HotMultiItem.VIEW_TYPE_WEEKLY_SALE -> {
                val adapter = SaleGameAdapter(mutableListOf(
                        SaleGameItem("游戏1", 23.3f, "冒险"),
                        SaleGameItem("游戏1", 23.3f, "冒险"),
                        SaleGameItem("游戏1", 23.3f, "冒险"),
                        SaleGameItem("游戏1", 23.3f, "冒险"),
                        SaleGameItem("游戏1", 23.3f, "冒险"),
                        SaleGameItem("游戏1", 23.3f, "冒险")
                ))
                helper.itemView.sale_game_list_rv.adapter = adapter
            }
            HotMultiItem.VIEW_TYPE_GAME_CATEGORY -> {
                val adapter = GameTypeAdapter(mutableListOf(
                        GameTypeItem("射击", R.drawable.ic_fps_color),
                        GameTypeItem("竞速", R.drawable.ic_racing_color),
                        GameTypeItem("角色扮演", R.drawable.ic_rpg_color),
                        GameTypeItem("音乐", R.drawable.ic_music_color),
                        GameTypeItem("益智", R.drawable.ic_puzzle_color),
                        GameTypeItem("休闲", R.drawable.ic_funny_color),
                        GameTypeItem("动作", R.drawable.ic_action_color),
                        GameTypeItem("冒险", R.drawable.ic_adventure_color),
                        GameTypeItem("卡牌", R.drawable.ic_card_color),
                        GameTypeItem("沙盒", R.drawable.ic_sandbox_color),
                        GameTypeItem("体育", R.drawable.ic_sport_color)
                )).apply {
                    setOnItemClickListener { adapter, view, position ->
                        activity.startActivity<CategoryActivity>()
                    }
                }
                val layoutManager = LinearLayoutManager(helper.itemView.context)
                layoutManager.orientation = LinearLayout.HORIZONTAL
                helper.itemView.game_type_rv.layoutManager = layoutManager
                helper.itemView.game_type_rv.adapter = adapter

            }
            HotMultiItem.VIEW_TYPE_GAME_SET -> {
                with(item.data as GameSetData, {
                    val data = this.games
                    helper.itemView.game_set_title.text = item.data.title
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
    }

}


open class BaseHotItemData
data class RecommendGameData(
        val gameName: String,
        val price: Float
) : BaseHotItemData()

class GameSetData(var games: List<Game>, val title: String) : BaseHotItemData() {
    var isFirst: Boolean = true
}

class HotViewHolder(itemView: View) : BaseViewHolder(itemView)
class HotMultiItem(val viewType: Int, val data: BaseHotItemData) : MultiItemEntity {
    override fun getItemType(): Int {
        return viewType
    }

    companion object {
        const val VIEW_TYPE_HOT_SALE = 0x001
        const val VIEW_TYPE_PROMOTIONS = 0x002
        const val VIEW_TYPE_WEEKLY_SALE = 0x003
        const val VIEW_TYPE_RECOMMENDS = 0x004
        const val VIEW_TYPE_GAME_CATEGORY = 0x005
        const val VIEW_TYPE_GAME_SET = 0x006
    }
}