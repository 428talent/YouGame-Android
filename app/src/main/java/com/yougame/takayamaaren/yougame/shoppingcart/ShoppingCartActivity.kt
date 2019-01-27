package com.yougame.takayamaaren.yougame.shoppingcart

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity;
import com.yougame.takayamaaren.yougame.R

import kotlinx.android.synthetic.main.activity_shopping_cart.*
import kotlinx.android.synthetic.main.content_shopping_cart.*

class ShoppingCartActivity : AppCompatActivity() {
    private val actionCallback = ShoppingCartActionMode()
    private val selectedList = mutableListOf<ShoppingCartItem>()
    private val itemList = mutableListOf(
            ShoppingCartItem(1),
            ShoppingCartItem(2),
            ShoppingCartItem(3),
            ShoppingCartItem(4),
            ShoppingCartItem(5),
            ShoppingCartItem(6),
            ShoppingCartItem(7),
            ShoppingCartItem(8),
            ShoppingCartItem(9),
            ShoppingCartItem(10)
    )
    private val adapter by lazy {
        ShoppingCartAdapter(itemList).apply {
            bindToRecyclerView(shopping_cart_rv)
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shopping_cart)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        toolbar.setNavigationOnClickListener {
            onBackPressed()
        }

        shopping_cart_rv.adapter = adapter
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
