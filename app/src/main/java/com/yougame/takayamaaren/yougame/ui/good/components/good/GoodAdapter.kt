package com.yougame.takayamaaren.yougame.ui.good.components.good

import com.chad.library.adapter.base.BaseQuickAdapter
import com.yougame.takayamaaren.yougame.R
import com.yougame.takayamaaren.yougame.sdk.model.response.Good
import com.yougame.takayamaaren.yougame.ui.base.SimpleViewHolder
import kotlinx.android.synthetic.main.item_select_good.view.*

class GoodAdapter(goods: MutableList<GoodItem>) : BaseQuickAdapter<GoodItem, SimpleViewHolder>(R.layout.item_select_good, goods) {
    var onGoodSelect: ((goodId: Int, selected: Boolean) -> Unit)? = null
    override fun convert(helper: SimpleViewHolder, item: GoodItem) {
        with(helper.itemView) {
            this.chip.chipText = item.good.name
            if (item.inCart) {
                chip.isSelectable = false
                chip.chipText = "${item.good.name}(在购物车内)"
            } else if (item.inInventory) {
                chip.isSelectable = false
                chip.chipText = "${item.good.name}(在仓库中)"
            }
            chip.setOnSelectClickListener { v, selected ->
                onGoodSelect?.invoke(item.good.id, selected)
            }
        }
    }
}

class GoodItem(val good: Good, var inCart: Boolean = false, var inInventory: Boolean = false) {

}