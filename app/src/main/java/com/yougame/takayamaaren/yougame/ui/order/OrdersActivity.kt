package com.yougame.takayamaaren.yougame.ui.order

import android.app.Activity
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.yougame.takayamaaren.yougame.R
import com.yougame.takayamaaren.yougame.sdk.model.response.Order
import kotlinx.android.synthetic.main.activity_orders.*
import kotlinx.android.synthetic.main.content_orders.*
import org.jetbrains.anko.startActivity

class OrdersActivity : AppCompatActivity(), OrdersView {
    private val adapter by lazy {
        OrdersAdapter(mutableListOf()).apply {
            bindToRecyclerView(rv_orders)
            setOnLoadMoreListener({
                presenter.loadMore()
            }, rv_orders)
        }
    }
    private val presenter: OrdersPresenter = OrdersPresenterImpl()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_orders)
        presenter.onAttach(this)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        toolbar.setNavigationOnClickListener {
            onBackPressed()
        }
        rv_orders.adapter = adapter
        presenter.loadData()
    }

    override fun onAddData(list: List<Order>) {
        adapter.addData(list.map { item -> OrderItem(item) })
        adapter.loadMoreComplete()
    }

    override fun onLoadData(list: List<Order>) {
        adapter.setNewData(list.map { item -> OrderItem(item) })
    }

    override fun onLoadEnd() {
        adapter.loadMoreEnd()
    }

    companion object {
        fun launch(activity : Activity){
            activity.startActivity<OrdersActivity>()
        }
    }
}
