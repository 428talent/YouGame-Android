package com.yougame.takayamaaren.yougame.ui.payment

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.yougame.takayamaaren.yougame.R
import kotlinx.android.synthetic.main.activity_payment.*
import kotlinx.android.synthetic.main.content_payment.*

class PaymentActivity : AppCompatActivity() {
    private val adapter by lazy {
        PaymentAdapter(mutableListOf(
                PaymentItem(),
                PaymentItem(),
                PaymentItem(),
                PaymentItem(),
                PaymentItem(),
                PaymentItem(),
                PaymentItem(),
                PaymentItem()
        )).apply {
            bindToRecyclerView(pay_rv)
        }
    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_payment)
        setSupportActionBar(toolbar)
        setSupportActionBar(toolbar)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        toolbar.setNavigationOnClickListener {
            onBackPressed()
        }
        pay_rv.adapter = adapter
    }

}
