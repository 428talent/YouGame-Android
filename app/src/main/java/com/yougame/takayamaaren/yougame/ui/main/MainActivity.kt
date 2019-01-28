package com.yougame.takayamaaren.yougame.ui.main

import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.view.Gravity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import com.yougame.takayamaaren.yougame.R
import com.yougame.takayamaaren.yougame.manager.BoxStoreManager
import com.yougame.takayamaaren.yougame.manager.user.UserManager
import com.yougame.takayamaaren.yougame.ui.account.AccountActivity
import com.yougame.takayamaaren.yougame.ui.friends.FriendsActivity
import com.yougame.takayamaaren.yougame.ui.login.LoginActivity
import com.yougame.takayamaaren.yougame.ui.main.feed.FeedFragment
import com.yougame.takayamaaren.yougame.ui.main.hot.HotFragment
import com.yougame.takayamaaren.yougame.ui.main.ranking.RankingFragment
import com.yougame.takayamaaren.yougame.ui.settings.SettingsActivity
import com.yougame.takayamaaren.yougame.ui.shoppingcart.ShoppingCartActivity
import com.yougame.takayamaaren.yougame.ui.wallet.WalletActivity
import com.yougame.takayamaaren.yougame.ui.wishlist.WishlistActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*
import kotlinx.android.synthetic.main.content_main.*
import kotlinx.android.synthetic.main.main_nav_header.view.*
import kotlinx.android.synthetic.main.nav_header_main.view.*
import org.jetbrains.anko.sdk27.coroutines.onClick
import org.jetbrains.anko.startActivity

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    private val hotFragment by lazy { HotFragment() }
    private val rankingFragment by lazy { RankingFragment() }
    private val feedFragment by lazy { FeedFragment() }
    private val developDialog by lazy {
        AlertDialog.Builder(this)
                .setItems(arrayOf("登陆", "注册")) { dialog, which ->
                    when (which) {
                        0 -> {
                            startActivity<LoginActivity>()
                        }
                    }
                }.setTitle("开发者")
                .create()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        BoxStoreManager.init(applicationContext)
        UserManager.loadUser()
        bar_search.isSelected = false

        val drawer = findViewById<View>(R.id.drawer_layout) as DrawerLayout
        main_search.onClick {
            developDialog.show()
        }
        main_toggle_drawer.onClick {
            drawer.openDrawer(Gravity.START)

        }

        nav_view.setNavigationItemSelectedListener(this)

        main_tab.setViewPager(main_pager, arrayOf("热门", "排行", "动态"), this, arrayListOf(hotFragment, rankingFragment, feedFragment))
        with(nav_view.getHeaderView(0).header) {

            UserManager.profile?.let {
                this.setAvatar(it.avatar)
            }
            UserManager.user?.let {
                this.setUsername("@${it.username}")
            }
        }


    }

    override fun onBackPressed() {
        val drawer = findViewById<View>(R.id.drawer_layout) as DrawerLayout
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        val id = item.itemId


        return if (id == R.id.action_settings) {
            true
        } else super.onOptionsItemSelected(item)

    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        val id = item.itemId

        when (id) {
            R.id.nav_friends ->
                startActivity<FriendsActivity>()
            R.id.nav_wishlist -> {
                startActivity<WishlistActivity>()
            }
            R.id.nav_shopping_cart -> {
                startActivity<ShoppingCartActivity>()
            }
            R.id.nav_wallet -> {
                startActivity<WalletActivity>()
            }
            R.id.nav_settings -> {
                startActivity<SettingsActivity>()
            }
        }
        val drawer = findViewById<View>(R.id.drawer_layout) as DrawerLayout
        drawer.closeDrawer(GravityCompat.START)
        return false
    }
}
