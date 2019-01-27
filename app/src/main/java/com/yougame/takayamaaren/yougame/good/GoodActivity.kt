package com.yougame.takayamaaren.yougame.good

import android.os.Bundle
import android.support.design.widget.AppBarLayout
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.bumptech.glide.Glide
import com.shuyu.gsyvideoplayer.GSYVideoManager
import com.shuyu.gsyvideoplayer.utils.OrientationUtils
import com.yougame.takayamaaren.yougame.Game
import com.yougame.takayamaaren.yougame.R
import com.yougame.takayamaaren.yougame.good.commonts.CommentsActivity
import kotlinx.android.synthetic.main.activity_good.*
import org.jetbrains.anko.sdk27.coroutines.onClick
import org.jetbrains.anko.startActivity


class GoodActivity : AppCompatActivity() {
    private val game by lazy {
        intent.getSerializableExtra("Game") as Game
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_good)
        setSupportActionBar(good_toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        good_toolbar.setNavigationOnClickListener {
            onBackPressed()
        }
        title = ""

        fab_good_comments.onClick {
            startActivity<CommentsActivity>()
        }

        bar_game_name.text = game.name
        Glide.with(this).load(game.cover).into(cover)
        card_info_game_name.text = game.name
        card_info_game_description.text = game.description

        detail_player.setUp("https://steamcdn-a.akamaihd.net/steam/apps/256711483/movie480.webm?t=1522328051", false, "预览")
        detail_player.titleTextView.visibility = View.VISIBLE
        detail_player.backButton.visibility = View.INVISIBLE
        detail_player.fullscreenButton.visibility = View.INVISIBLE
        val orientationUtils = OrientationUtils(this, detail_player)
        detail_player.fullscreenButton.setOnClickListener { orientationUtils.resolveByClick() }
        detail_player.setIsTouchWiget(true)
        detail_player.backButton.setOnClickListener { onBackPressed() }

        app_bar_layout.addOnOffsetChangedListener(object : AppBarLayout.OnOffsetChangedListener {
            override fun onOffsetChanged(appBarLayout: AppBarLayout, verticalOffset: Int) {
                takeIf { Math.abs(verticalOffset) >= appBarLayout.totalScrollRange }?.let {
                    title = game.name

                } ?: run {
                    title = ""
                }
            }

        })

    }

    override fun onBackPressed() {
        if (GSYVideoManager.backFromWindowFull(this)) {
            return
        }
        super.onBackPressed()
    }

    override fun onPause() {
        super.onPause()
        GSYVideoManager.onPause()
    }

    override fun onResume() {
        super.onResume()
        GSYVideoManager.onResume()
    }

    override fun onDestroy() {
        super.onDestroy()
        GSYVideoManager.releaseAllVideos()
    }

}
