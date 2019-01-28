package com.yougame.takayamaaren.yougame.ui.account.fragments.feeds

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.yougame.takayamaaren.yougame.R
import kotlinx.android.synthetic.main.fragment_account_feeds.view.*


class AccountFeedsFragment : Fragment() {
    // TODO: Rename and change types of parameters

    private val adapter by lazy {
        AccountFeedsAdapter(mutableListOf()).apply {
            bindToRecyclerView(view?.account_feed_rv)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_account_feeds, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.account_feed_rv.adapter = adapter
        adapter.setNewData(mutableListOf(
                AccountFeedsItem(),
                AccountFeedsItem(),
                AccountFeedsItem(),
                AccountFeedsItem(),
                AccountFeedsItem(),
                AccountFeedsItem(),
                AccountFeedsItem(),
                AccountFeedsItem()
        ))
    }

    companion object {

        @JvmStatic
        fun newInstance(param1: String, param2: String) =
                AccountFeedsFragment().apply {
                    arguments = Bundle().apply {

                    }
                }
    }
}
