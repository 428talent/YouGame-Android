package com.yougame.takayamaaren.yougame.ui.wallet

import android.os.Bundle
import android.support.v7.app.AppCompatActivity;
import com.yougame.takayamaaren.yougame.R

import kotlinx.android.synthetic.main.activity_wallet.*
import kotlinx.android.synthetic.main.content_wallet.*

class WalletActivity : AppCompatActivity() {
    private val adapter by lazy {
        WalletAdapter(mutableListOf(
                WalletListItem(),
                WalletListItem(),
                WalletListItem(),
                WalletListItem(),
                WalletListItem(),
                WalletListItem(),
                WalletListItem(),
                WalletListItem(),
                WalletListItem(),
                WalletListItem(),
                WalletListItem(),
                WalletListItem()
        )).apply {
            bindToRecyclerView(wallet_rv)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_wallet)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        toolbar.setNavigationOnClickListener {
            onBackPressed()
        }
        wallet_rv.adapter = adapter
    }

}
