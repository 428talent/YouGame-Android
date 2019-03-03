package com.yougame.takayamaaren.yougame.ui.order

import com.chad.library.adapter.base.BaseQuickAdapter
import com.yougame.takayamaaren.yougame.R
import com.yougame.takayamaaren.yougame.sdk.model.response.Order
import com.yougame.takayamaaren.yougame.ui.base.SimpleViewHolder
import com.yougame.takayamaaren.yougame.utils.DateUtils
import kotlinx.android.synthetic.main.item_order.view.*

class OrdersAdapter(orders: MutableList<OrderItem>) : BaseQuickAdapter<OrderItem, SimpleViewHolder>(R.layout.item_order, orders) {
    override fun convert(helper: SimpleViewHolder, item: OrderItem) {
        helper.itemView.let { itemView ->
            item.data.let { order ->
                itemView.tv_order_id.text = "订单号: ${order.id}"
                itemView.tv_state.text = order.state
                itemView.tv_time.text = DateUtils.toDateString(order.created)
            }
        }
    }
}

data class OrderItem(val data: Order)