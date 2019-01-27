package com.yougame.takayamaaren.yougame.wishlist

import android.graphics.Color
import android.view.View
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.yougame.takayamaaren.yougame.R
import kotlinx.android.synthetic.main.card_wishlist_item.view.*
import org.jetbrains.anko.backgroundColor
import org.jetbrains.anko.sdk27.coroutines.onCheckedChange
import org.jetbrains.anko.sdk27.coroutines.onClick


class WishlistAdapter(val datas: MutableList<WishlistItem>) : BaseQuickAdapter<WishlistItem, WishlistViewHolder>(R.layout.card_wishlist_item, datas) {
    var onItemSelectChange: ((item: WishlistItem, isSelect: Boolean) -> Unit)? = null
    private val selectedOverlayColor = Color.parseColor("#347cff")
    private val notSelectOverlayColor = Color.parseColor("#2a2a2a")
    override fun convert(helper: WishlistViewHolder, item: WishlistItem) {
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
        helper.itemView.item_check.onCheckedChange { _, isChecked ->
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

class WishlistItem(val itemId: Int) {
    var isActiveSelectMode = false
    var isSelected = false
    var onStartSelectMode: (() -> Unit)? = null
    var onExitSelectMode: (() -> Unit)? = null
    var setIsSelected: ((isSelected: Boolean) -> Unit)? = null
}

class WishlistViewHolder(itemView: View) : BaseViewHolder(itemView)