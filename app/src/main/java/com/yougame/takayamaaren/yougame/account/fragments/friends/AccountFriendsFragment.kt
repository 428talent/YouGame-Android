package com.yougame.takayamaaren.yougame.account.fragments.friends


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.yougame.takayamaaren.yougame.R
import kotlinx.android.synthetic.main.fragment_account_friends.view.*


class AccountFriendsFragment : Fragment() {
    private val adapter by lazy {
        AccountFriendsAdapter(mutableListOf(
                AccountFriendsItem(),
                AccountFriendsItem(),
                AccountFriendsItem(),
                AccountFriendsItem(),
                AccountFriendsItem(),
                AccountFriendsItem(),
                AccountFriendsItem(),
                AccountFriendsItem(),
                AccountFriendsItem(),
                AccountFriendsItem(),
                AccountFriendsItem(),
                AccountFriendsItem(),
                AccountFriendsItem(),
                AccountFriendsItem(),
                AccountFriendsItem(),
                AccountFriendsItem(),
                AccountFriendsItem(),
                AccountFriendsItem(),
                AccountFriendsItem(),
                AccountFriendsItem(),
                AccountFriendsItem(),
                AccountFriendsItem(),
                AccountFriendsItem(),
                AccountFriendsItem(),
                AccountFriendsItem(),
                AccountFriendsItem(),
                AccountFriendsItem(),
                AccountFriendsItem(),
                AccountFriendsItem(),
                AccountFriendsItem(),
                AccountFriendsItem(),
                AccountFriendsItem(),
                AccountFriendsItem(),
                AccountFriendsItem(),
                AccountFriendsItem(),
                AccountFriendsItem(),
                AccountFriendsItem(),
                AccountFriendsItem(),
                AccountFriendsItem(),
                AccountFriendsItem(),
                AccountFriendsItem(),
                AccountFriendsItem(),
                AccountFriendsItem(),
                AccountFriendsItem(),
                AccountFriendsItem(),
                AccountFriendsItem(),
                AccountFriendsItem(),
                AccountFriendsItem(),
                AccountFriendsItem(),
                AccountFriendsItem(),
                AccountFriendsItem(),
                AccountFriendsItem(),
                AccountFriendsItem(),
                AccountFriendsItem(),
                AccountFriendsItem(),
                AccountFriendsItem(),
                AccountFriendsItem(),
                AccountFriendsItem(),
                AccountFriendsItem(),
                AccountFriendsItem()
        )).apply {
            bindToRecyclerView(view?.account_friends_rv)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_account_friends, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val layoutManager = GridLayoutManager(this.context, 3)
        view.account_friends_rv.layoutManager = layoutManager
        view.account_friends_rv.adapter = adapter
    }

}
