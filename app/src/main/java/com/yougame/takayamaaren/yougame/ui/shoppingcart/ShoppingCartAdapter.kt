package com.yougame.takayamaaren.yougame.ui.shoppingcart

import android.graphics.Color
import android.view.View
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.yougame.takayamaaren.yougame.R
import kotlinx.android.synthetic.main.card_shopping_cart.view.*
import org.jetbrains.anko.sdk27.coroutines.onCheckedChange
import org.jetbrains.anko.sdk27.coroutines.onClick


class ShoppingCartAdapter(val datas: MutableList<ShoppingCartItem>) : BaseQuickAdapter<ShoppingCartItem, ShoppingCartViewHolder>(R.layout.card_shopping_cart, datas) {
    var onItemSelectChange: ((item: ShoppingCartItem, isSelect: Boolean) -> Unit)? = null
    private val selectedOverlayColor = Color.parseColor("#347cff")
    private val notSelectOverlayColor = Color.parseColor("#2a2a2a")
    override fun convert(helper: ShoppingCartViewHolder, item: ShoppingCartItem) {
        fun refreshCardState() {
            takeIf { item.isActiveSelectMode }?.let {
                helper.itemView.item_select_overlay.visibility = View.VISIBLE
                helper.itemView.item_check.visibility = View.VISIBLE
            } ?: run {
                helper.itemView.item_select_overlay.visibility = View.GONE
                helper.itemView.item_check.visibility = View.GONE
            }
            helper.itemView.item_check.isChecked = item.isSelected
            takeIf { item.isSelected }?.let {
                helper.itemView.item_select_overlay.setBackgroundColor(selectedOverlayColor)
            } ?: run {
                helper.itemView.item_select_overlay.setBackgroundColor(notSelectOverlayColor)
            }
        }
        item.onStartSelectMode = {
            item.isActiveSelectMode = true
            refreshCardState()
        }
        item.onExitSelectMode = {
            item.isActiveSelectMode = false
            refreshCardState()
        }
        helper.itemView.item_check.onCheckedChange { buttonView, isChecked ->
            onItemSelectChange?.invoke(item, isChecked)
            item.isSelected = isChecked
            refreshCardState()
        }
        helper.itemView.item_select_overlay.onClick {
            onItemSelectChange?.invoke(item, !item.isSelected)
            item.isSelected = !item.isSelected
            refreshCardState()
        }
        item.setIsSelected = {
            item.isSelected = it
            refreshCardState()
        }

        refreshCardState()


    }

}

class ShoppingCartItem(val itemId: Int) {
    var isActiveSelectMode = false
    var isSelected = false
    var onStartSelectMode: (() -> Unit)? = null
    var onExitSelectMode: (() -> Unit)? = null
    var setIsSelected: ((isSelected: Boolean) -> Unit)? = null
}

class ShoppingCartViewHolder(itemView: View) : BaseViewHolder(itemView)