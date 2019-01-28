package com.yougame.takayamaaren.yougame.ui.main.feed

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.yougame.takayamaaren.yougame.R
import kotlinx.android.synthetic.main.fragment_feed.view.*


class FeedFragment : Fragment() {

    private val adapter by lazy {
        FeedAdapter(mutableListOf(
                FeedMultiItem(FeedMultiItem.VIEW_TYPE_GAME),
                FeedMultiItem(FeedMultiItem.VIEW_TYPE_GAME),
                FeedMultiItem(FeedMultiItem.VIEW_TYPE_COMMENT),
                FeedMultiItem(FeedMultiItem.VIEW_TYPE_GAME),
                FeedMultiItem(FeedMultiItem.VIEW_TYPE_COMMENT),
                FeedMultiItem(FeedMultiItem.VIEW_TYPE_GAME)
                )).apply {
            bindToRecyclerView(view?.feed_rv)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_feed, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.feed_rv.adapter = adapter
    }


    companion object {

        @JvmStatic
        fun newInstance() =
                FeedFragment().apply {
                    arguments = Bundle().apply {

                    }
                }
    }
}
