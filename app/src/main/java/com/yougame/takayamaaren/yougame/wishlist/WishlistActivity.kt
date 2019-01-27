package com.yougame.takayamaaren.yougame.wishlist

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.yougame.takayamaaren.yougame.R
import kotlinx.android.synthetic.main.activity_wishlist.*
import kotlinx.android.synthetic.main.content_wishlist.*

class WishlistActivity : AppCompatActivity() {
    private val actionCallback = GoodActionMode()
    private val selectedList = mutableListOf<WishlistItem>()
    private val itemList = mutableListOf(
            WishlistItem(1),
            WishlistItem(2),
            WishlistItem(3),
            WishlistItem(4),
            WishlistItem(5),
            WishlistItem(6),
            WishlistItem(7),
            WishlistItem(8),
            WishlistItem(9),
            WishlistItem(10)
    )
    private val adapter by lazy {
        WishlistAdapter(itemList).apply {
            bindToRecyclerView(wishlist_rv)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_wishlist)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        toolbar.setNavigationOnClickListener {
            onBackPressed()
        }

        wishlist_rv.adapter = adapter
        adapter.setOnItemLongClickListener { _, view, position ->

            adapter.datas.forEach {
                takeIf { _ -> selectedList.any { selectItem -> selectItem == it } }?.let { _ ->
                    it.isSelected = true
                    it.setIsSelected?.invoke(true)
                }
                it.isActiveSelectMode = true
                it.onStartSelectMode?.invoke()
            }
            toolbar.startActionMode(actionCallback)
            true
        }
        adapter.onItemSelectChange = { itemId, isSelect ->
            takeIf { isSelect }?.let { selectedList.add(itemId) }
                    ?: run { selectedList.remove(itemId) }
        }
        actionCallback.onExitMode = {
            adapter.datas.forEach { it.onExitSelectMode?.invoke() }
        }

        actionCallback.onSelectAll = {
            selectedList.clear()
            adapter.datas.forEach {
                it.isSelected = true
                it.setIsSelected?.invoke(true)
                selectedList.add(it)
            }
        }
        actionCallback.onDeselectAll = {
            selectedList.clear()
            adapter.datas.forEach {
                it.isSelected = false
                it.setIsSelected?.invoke(false)
            }
        }
        actionCallback.onDelete = {mode ->
            selectedList.forEach { selectedItem ->
                adapter.datas.find { it.itemId == selectedItem.itemId }?.let {
                    adapter.remove(adapter.datas.indexOf(it))
                }

            }
            mode.finish()
        }

    }

}
