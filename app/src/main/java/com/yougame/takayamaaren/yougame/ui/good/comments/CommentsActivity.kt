package com.yougame.takayamaaren.yougame.ui.good.comments

import android.app.Activity
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity;
import com.yougame.takayamaaren.yougame.R
import com.yougame.takayamaaren.yougame.sdk.model.response.Comment

import kotlinx.android.synthetic.main.activity_comments.*
import kotlinx.android.synthetic.main.content_comments.*
import org.jetbrains.anko.startActivity

class CommentsActivity : AppCompatActivity(), CommentsView {
    private val adapter by lazy {
        CommentsAdapter(mutableListOf()).apply {
            bindToRecyclerView(rv)

        }
    }
    private val presenter: CommentPresenter = CommentPresenterImpl()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_comments)
        presenter.onAttach(this)
        setSupportActionBar(toolbar)
        intent.getIntExtra("GameId", 0).let {
            if (it != 0) {
                presenter.initPresenter(it)
                presenter.loadComments()
            }
        }
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        toolbar.setNavigationOnClickListener {
            onBackPressed()
        }
        rv.adapter = adapter

    }

    override fun setComments(list: List<Comment>) {
        adapter.setNewData(list.map { item -> CommentItem(item) }.toMutableList())
    }

    companion object {
        fun launch(activity: Activity, gameId: Int) {
            activity.startActivity<CommentsActivity>("GameId" to gameId)
        }
    }

}
