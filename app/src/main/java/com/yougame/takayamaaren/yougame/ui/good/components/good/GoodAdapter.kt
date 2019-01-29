package com.yougame.takayamaaren.yougame.ui.good.components.good

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter
import com.chad.library.adapter.base.BaseQuickAdapter
import com.yougame.takayamaaren.yougame.R
import com.yougame.takayamaaren.yougame.sdk.model.response.Good
import com.yougame.takayamaaren.yougame.ui.base.SimpleViewHolder
import kotlinx.android.synthetic.main.item_select_good.view.*

class GoodAdapter(goods: MutableList<GoodItem>) : BaseQuickAdapter<GoodItem, SimpleViewHolder>(R.layout.item_select_good, goods) {
    override fun convert(helper: SimpleViewHolder, item: GoodItem) {
        with(helper.itemView) {
            this.chip.chipText = item.good.name
        }
    }
}

class GoodItem(val good: Good) {

}