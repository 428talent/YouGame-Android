package com.yougame.takayamaaren.yougame.ui.order

import com.yougame.takayamaaren.yougame.sdk.model.response.Order
import com.yougame.takayamaaren.yougame.ui.base.View

interface OrdersView : View {
    fun onLoadData(list : List<Order>)
    fun onAddData(list : List<Order>)
    fun onLoadEnd()
}