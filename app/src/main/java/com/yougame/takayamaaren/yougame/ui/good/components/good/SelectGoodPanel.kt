package com.yougame.takayamaaren.yougame.ui.good.components.good

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.LinearLayout
import com.yougame.takayamaaren.yougame.R
import com.yougame.takayamaaren.yougame.sdk.model.response.Good
import kotlinx.android.synthetic.main.panel_good_slide.view.*
import org.jetbrains.anko.sdk27.coroutines.onClick

class SelectGoodPanel(context: Context, attributeSet: AttributeSet) : LinearLayout(context, attributeSet) {
    var goods = mutableListOf<Good>()
    private val selectedGoodIds = mutableListOf<Int>()
    var onAddToCart: ((goodIds: List<Int>) -> Unit)? = null
    var totalPrice = 0.0
    private val adapter by lazy {
        GoodAdapter(mutableListOf()).apply {
            bindToRecyclerView(rv_goods)
        }
    }

    fun updateGoods(goods: List<GoodItem>) {
        this.goods = goods.map { item -> item.good }.toMutableList()
        adapter.setNewData(goods)
    }

    fun setTitleVisitable(visitable: Boolean) {
        title.visibility = if (visitable) View.VISIBLE else View.GONE
    }

    init {
        inflate(context, R.layout.panel_good_slide, this)
        updateTotalPrice()
        adapter.onGoodSelect = { goodId: Int, selected: Boolean ->
            if (selected) selectedGoodIds.add(goodId) else selectedGoodIds.remove(goodId)
            updateTotalPrice()
        }
        btn_add_cart.onClick {
            onAddToCart?.invoke(selectedGoodIds)
        }
        rv_goods.setHasFixedSize(true)

    }

    private fun updateTotalPrice() {
        totalPrice = goods.filter { good: Good -> selectedGoodIds.any { id -> id == good.id } }.sumByDouble { good -> good.price }
        tv_total_price.text = "¥$totalPrice"
    }

}