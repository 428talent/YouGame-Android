package com.yougame.takayamaaren.yougame.ui.shoppingcart

import android.app.Activity
import android.os.Bundle
import android.support.v7.app.AppCompatActivity;
import com.yougame.takayamaaren.yougame.R
import com.yougame.takayamaaren.yougame.ui.good.GoodActivity

import kotlinx.android.synthetic.main.activity_shopping_cart.*
import kotlinx.android.synthetic.main.content_shopping_cart.*
import org.jetbrains.anko.startActivity

class ShoppingCartActivity : AppCompatActivity(), CartView {


    private val actionCallback = ShoppingCartActionMode()
    private val selectedList = mutableListOf<ShoppingCartItem>()
    private val adapter by lazy {
        ShoppingCartAdapter(mutableListOf()).apply {
            bindToRecyclerView(shopping_cart_rv)
        }
    }
    private val presenter: CartPresenter = CartPresenterImpl()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shopping_cart)
        presenter.onAttach(this)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        toolbar.setNavigationOnClickListener {
            onBackPressed()
        }
        initView()
        presenter.getWishlistItems()


    }

    private fun initView() {

        shopping_cart_rv.adapter = adapter
        adapter.setOnItemLongClickListener { _, view, position ->

            adapter.data.forEach {
                takeIf { _ -> selectedList.any { selectItem -> selectItem == it } }?.let { _ ->
                    it.isSelected = true
                    it.setIsSelected?.invoke(true)
                }

                it.onStartSelectMode?.invoke()
                it.isActiveSelectMode = true
            }
            toolbar.startActionMode(actionCallback)
            true
        }
        adapter.setOnItemClickListener { adapter, view, position ->
            val data = adapter.data[position] as ShoppingCartItem
            GoodActivity.launch(this, data.gameId)
        }
        adapter.onItemSelectChange = { itemId, isSelect ->
            takeIf { isSelect }?.let { selectedList.add(itemId) }
                    ?: run { selectedList.remove(itemId) }
        }
        actionCallback.onExitMode = {
            adapter.data.forEach { it.onExitSelectMode?.invoke() }
        }

        actionCallback.onSelectAll = {
            selectedList.clear()
            adapter.data.forEach {
                it.isSelected = true
                it.setIsSelected?.invoke(true)
                selectedList.add(it)
            }
        }
        actionCallback.onDeselectAll = {
            selectedList.clear()
            adapter.data.forEach {
                it.isSelected = false
                it.setIsSelected?.invoke(false)
            }
        }
        actionCallback.onDelete = { mode ->
            presenter.deleteItem(selectedList.map { item -> item.itemId })
            mode.finish()
        }
    }

    override fun onLoadCartItemComplete(items: List<ShoppingCartItem>) {
        adapter.setNewData(items)
    }

    override fun removeCartItems(ids: List<Int>) {
        ids.forEach { cartItemId ->
            val pos = adapter.data.indexOfFirst { item -> item.itemId == cartItemId }
            if (pos != -1) {
                adapter.remove(pos)
            }
            selectedList.removeAll { item ->
                item.itemId == cartItemId
            }

        }

    }

    companion object {
        fun launch(activity: Activity) {
            activity.startActivity<ShoppingCartActivity>()
        }
    }

}
