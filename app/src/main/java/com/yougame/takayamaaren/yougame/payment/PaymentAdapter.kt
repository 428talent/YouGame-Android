package com.yougame.takayamaaren.yougame.payment

import android.view.View
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.yougame.takayamaaren.yougame.R

class PaymentAdapter(val datas : MutableList<PaymentItem>) : BaseQuickAdapter<PaymentItem, PaymentViewHolder>(R.layout.item_payment_good,datas) {
    override fun convert(helper: PaymentViewHolder?, item: PaymentItem?) {

    }
}

class PaymentItem

class PaymentViewHolder(itemView : View) : BaseViewHolder(itemView)