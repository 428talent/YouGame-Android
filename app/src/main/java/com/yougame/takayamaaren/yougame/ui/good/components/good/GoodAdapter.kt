package com.yougame.takayamaaren.yougame.ui.good.components.good

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter
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
            chip.setOnSelectClickListener { v, selected ->
                onGoodSelect?.invoke(item.good.id, selected)
            }
        }
    }
}

class GoodItem(val good: Good) {

}