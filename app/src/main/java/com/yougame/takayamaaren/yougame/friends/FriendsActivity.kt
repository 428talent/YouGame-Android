package com.yougame.takayamaaren.yougame.friends

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.yougame.takayamaaren.yougame.R
import com.yougame.takayamaaren.yougame.chat.ChatActivity
import kotlinx.android.synthetic.main.activity_friends.*
import kotlinx.android.synthetic.main.content_friends.*
import org.jetbrains.anko.startActivity

class FriendsActivity : AppCompatActivity() {
    private val mockFriends = mutableListOf<FriendItem>().apply {
        for (idx in 1..10) {
            add(FriendItem("", "好友$idx"))
        }
    }
    private val adapter by lazy {
        FriendAdapter(mockFriends)
                .apply {
                    bindToRecyclerView(friends_rv)
                    setOnItemClickListener { _, _, _ ->
                        startActivity<ChatActivity>()
                    }
                }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_friends)
        setSupportActionBar(toolbar)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        toolbar.setNavigationOnClickListener {
            onBackPressed()
        }
        title = "朋友"

        friends_rv.adapter = adapter
    }

}
