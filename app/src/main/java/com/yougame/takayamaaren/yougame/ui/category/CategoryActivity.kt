package com.yougame.takayamaaren.yougame.ui.category

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.yougame.takayamaaren.yougame.Mock
import com.yougame.takayamaaren.yougame.R
import kotlinx.android.synthetic.main.activity_category.*

class CategoryActivity : AppCompatActivity() {
    private val adapter by lazy {
        CategoryAdapter(mutableListOf(
                CategoryItem(Mock.ubisoftGame, "最多购买"),
                CategoryItem(Mock.ubisoftGame, "优惠中"),
                CategoryItem(Mock.gameList, "最新发售"),
                CategoryItem(Mock.gameList, "免费游戏"),
                CategoryItem(Mock.gameList, "接受预购"),
                CategoryItem(Mock.gameList, "为您推荐")
        ), this).apply {
            bindToRecyclerView(category_rv)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        toolbar.setNavigationOnClickListener {
            onBackPressed()
        }
        category_rv.adapter = adapter
        title = "冒险"

    }

}
