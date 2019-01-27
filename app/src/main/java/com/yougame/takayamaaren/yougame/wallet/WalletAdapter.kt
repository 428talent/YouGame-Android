package com.yougame.takayamaaren.yougame.wallet

import android.view.View
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.yougame.takayamaaren.yougame.R

class WalletAdapter(val datas : MutableList<WalletListItem>) : BaseQuickAdapter<WalletListItem, WalletViewHolder>(R.layout.item_wallet_item,datas) {
    override fun convert(helper: WalletViewHolder?, item: WalletListItem?) {

    }
}

class WalletListItem

class WalletViewHolder(itemView : View) : BaseViewHolder(itemView)