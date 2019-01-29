package com.yougame.takayamaaren.yougame.ui.good.components.good

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.LinearLayout
import com.yougame.takayamaaren.yougame.R
import com.yougame.takayamaaren.yougame.sdk.model.response.Good
import kotlinx.android.synthetic.main.panel_good_slide.view.*

class SelectGoodPanel(context: Context, attributeSet: AttributeSet) : LinearLayout(context, attributeSet) {
    var goods = mutableListOf<Good>()
    private val adapter by lazy {
        GoodAdapter(mutableListOf()).apply {
            bindToRecyclerView(rv_goods)
        }
    }

    fun updateGoods(goods: List<Good>) {
        this.goods = goods.toMutableList()
        adapter.setNewData(goods.map { good -> GoodItem(good) })
    }

    init {
        inflate(context, R.layout.panel_good_slide, this)

    }

}