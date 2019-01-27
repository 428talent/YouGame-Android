package com.yougame.takayamaaren.yougame.gamelist

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity;
import com.yougame.takayamaaren.yougame.R

import kotlinx.android.synthetic.main.activity_good_list.*
import kotlinx.android.synthetic.main.content_good_list.*

class GoodListActivity : AppCompatActivity() {
    private val adapter by lazy {
        GoodListAdapter(mutableListOf(
                GoodListItem(),
                GoodListItem(),
                GoodListItem(),
                GoodListItem(),
                GoodListItem(),
                GoodListItem(),
                GoodListItem(),
                GoodListItem(),
                GoodListItem(),
                GoodListItem()
        )).apply {
            bindToRecyclerView(good_list_rv)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_good_list)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        toolbar.setNavigationOnClickListener {
            onBackPressed()
        }
        title = ""
        good_list_rv.adapter = adapter

    }

}
