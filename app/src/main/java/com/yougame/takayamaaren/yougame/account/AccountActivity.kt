package com.yougame.takayamaaren.yougame.account

import android.os.Bundle
import android.support.design.widget.AppBarLayout
import android.support.v7.app.AppCompatActivity;
import com.yougame.takayamaaren.yougame.R
import com.yougame.takayamaaren.yougame.account.fragments.feeds.AccountFeedsFragment
import com.yougame.takayamaaren.yougame.account.fragments.friends.AccountFriendsFragment
import com.yougame.takayamaaren.yougame.account.fragments.AccountInventoryFragment

import kotlinx.android.synthetic.main.activity_account.*

class AccountActivity : AppCompatActivity() {
    private val feedsFragment by lazy { AccountFeedsFragment() }
    private val inventoryFragment by lazy { AccountInventoryFragment() }
    private val friendsFragment by lazy { AccountFriendsFragment() }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_account)
        setSupportActionBar(account_toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        account_toolbar.setNavigationOnClickListener {
            onBackPressed()
        }
        account_tab.setViewPager(account_page, arrayOf("动态", "游戏", "好友"), this, arrayListOf(feedsFragment, inventoryFragment, friendsFragment))

        app_bar_layout.addOnOffsetChangedListener(object : AppBarLayout.OnOffsetChangedListener {
            override fun onOffsetChanged(appBarLayout: AppBarLayout, verticalOffset: Int) {
                takeIf { Math.abs(verticalOffset) >= appBarLayout.totalScrollRange }?.let {
                    title = "Aren Takayama"

                } ?: run {
                    title = "用户"
                }
            }

        })

    }

}
