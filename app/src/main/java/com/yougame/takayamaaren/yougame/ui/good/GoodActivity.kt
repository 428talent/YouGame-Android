package com.yougame.takayamaaren.yougame.ui.good

import android.app.Activity
import android.os.Bundle
import android.support.design.widget.AppBarLayout
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.bumptech.glide.Glide
import com.shuyu.gsyvideoplayer.GSYVideoManager
import com.shuyu.gsyvideoplayer.utils.OrientationUtils
import com.sothree.slidinguppanel.SlidingUpPanelLayout
import com.yougame.takayamaaren.yougame.R
import com.yougame.takayamaaren.yougame.sdk.model.response.Good
import com.yougame.takayamaaren.yougame.ui.good.comments.CommentsActivity
import com.yougame.takayamaaren.yougame.ui.good.components.comment.CommentItem
import kotlinx.android.synthetic.main.activity_good.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.jetbrains.anko.sdk27.coroutines.onClick
import org.jetbrains.anko.startActivity


class GoodActivity : AppCompatActivity(), GameView {

    private val presenter: GamePresenter = GamePresenterImpl()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_good)
        presenter.onAttach(this)
        setSupportActionBar(good_toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        good_toolbar.setNavigationOnClickListener {
            onBackPressed()
        }
        title = ""

        fab_good_comments.onClick {
            startActivity<CommentsActivity>()
        }




        sliding_layout.addPanelSlideListener(object : SlidingUpPanelLayout.PanelSlideListener {
            override fun onPanelSlide(panel: View?, slideOffset: Float) {

            }

            override fun onPanelStateChanged(panel: View?, previousState: SlidingUpPanelLayout.PanelState?, newState: SlidingUpPanelLayout.PanelState) {
                fab_menu.visibility = if (newState == SlidingUpPanelLayout.PanelState.EXPANDED || newState == SlidingUpPanelLayout.PanelState.DRAGGING) View.GONE else View.VISIBLE
            }
        })

        intent.getIntExtra("GameId", 0).let {
            if (it > 0) {
                presenter.loadGame(it)
                presenter.loadGoods(it)
                presenter.loadComments(it)
            }
        }

    }

    override fun onGameCoverLoad(url: String) {
        Glide.with(this).load(url).into(cover)
    }

    override fun onGameLoad(game: com.yougame.takayamaaren.yougame.sdk.model.response.Game) {
        bar_game_name.text = game.name


        card_info_game_description.text = game.intro
        app_bar_layout.addOnOffsetChangedListener(AppBarLayout.OnOffsetChangedListener { appBarLayout, verticalOffset ->
            GlobalScope.launch(Dispatchers.Main) {
                title = if (Math.abs(verticalOffset) >= appBarLayout.totalScrollRange)
                    game.name
                else
                    ""
            }
        })
    }

    override fun onCommentLoad(comment: List<CommentItem>) {
        card_comments.updateComments(comment)
    }

    override fun onGoodsLoad(good: List<Good>) {
        panel_goods.updateGoods(good)
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

    companion object {
        fun launch(activity: Activity, gameId: Int) {
            activity.startActivity<GoodActivity>("GameId" to gameId)
        }
    }

}
