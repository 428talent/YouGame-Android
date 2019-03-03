package com.yougame.takayamaaren.yougame.ui.order

import com.yougame.takayamaaren.yougame.sdk.model.response.Container
import com.yougame.takayamaaren.yougame.sdk.model.response.Order
import com.yougame.takayamaaren.yougame.services.order.OrderQueryBuilder
import com.yougame.takayamaaren.yougame.ui.base.Presenter
import com.yougame.takayamaaren.yougame.utils.ItemLoader
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

interface OrdersPresenter : Presenter<OrdersView> {
    fun loadData()
    fun loadMore()
}

class OrdersPresenterImpl : OrdersPresenter {
    private lateinit var view: OrdersView
    private val dataLoader = OrderItemLoader().apply {
        onLoadData = {
            GlobalScope.launch(Dispatchers.Main) {
                view.onLoadData(it)
            }
        }
        onLoadMore = {
            GlobalScope.launch(Dispatchers.Main) {
                view.onAddData(it)
            }
        }
        onLoadEnd = {
            GlobalScope.launch(Dispatchers.Main) {
                view.onLoadEnd()
            }
        }
    }

    override fun loadData() {
        dataLoader.loadData()
    }

    override fun loadMore() {
        dataLoader.loadMore()
    }

    override fun onAttach(view: OrdersView) {
        this.view = view
    }

    override fun onDetach() {

    }

    class OrderItemLoader : ItemLoader<Order>() {
        override suspend fun queryData(page: Int, pageSize: Int): Container<Order> {
            return OrderQueryBuilder().inPage(page, pageSize).query()

        }
    }
}