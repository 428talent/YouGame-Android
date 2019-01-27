package com.yougame.takayamaaren.yougame.chat

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity;
import com.yougame.takayamaaren.yougame.R

import kotlinx.android.synthetic.main.activity_chat.*
import kotlinx.android.synthetic.main.content_chat.*

class ChatActivity : AppCompatActivity() {
    private val adapter by lazy {
        ChatAdapter(mutableListOf(
                ChatMultiItem(ChatMultiItem.VIEW_TYPE_MESSAGE),
                ChatMultiItem(ChatMultiItem.VIEW_TYPE_MESSAGE),
                ChatMultiItem(ChatMultiItem.VIEW_TYPE_SELF_MESSAGE),
                ChatMultiItem(ChatMultiItem.VIEW_TYPE_MESSAGE),
                ChatMultiItem(ChatMultiItem.VIEW_TYPE_SELF_MESSAGE),
                ChatMultiItem(ChatMultiItem.VIEW_TYPE_MESSAGE),
                ChatMultiItem(ChatMultiItem.VIEW_TYPE_SELF_MESSAGE),
                ChatMultiItem(ChatMultiItem.VIEW_TYPE_SELF_MESSAGE),
                ChatMultiItem(ChatMultiItem.VIEW_TYPE_MESSAGE),
                ChatMultiItem(ChatMultiItem.VIEW_TYPE_MESSAGE),
                ChatMultiItem(ChatMultiItem.VIEW_TYPE_MESSAGE),
                ChatMultiItem(ChatMultiItem.VIEW_TYPE_MESSAGE),
                ChatMultiItem(ChatMultiItem.VIEW_TYPE_MESSAGE),
                ChatMultiItem(ChatMultiItem.VIEW_TYPE_SELF_MESSAGE),
                ChatMultiItem(ChatMultiItem.VIEW_TYPE_SELF_MESSAGE),
                ChatMultiItem(ChatMultiItem.VIEW_TYPE_SELF_MESSAGE)
        )).apply {
            bindToRecyclerView(chat_rv)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        toolbar.setNavigationOnClickListener {
            onBackPressed()
        }
        title = "与Alex聊天中"
        chat_rv.adapter = adapter
    }

}
