package com.yougame.takayamaaren.yougame.ui.main.ranking


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_ranking.view.*
import com.yougame.takayamaaren.yougame.R


class RankingFragment : Fragment() {
    private val adapter by lazy {
        RankingAdapter(mutableListOf(
                RankingItem(),
                RankingItem(),
                RankingItem(),
                RankingItem(),
                RankingItem(),
                RankingItem(),
                RankingItem(),
                RankingItem()
        )).apply {
            bindToRecyclerView(view?.ranking_rv)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_ranking, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.ranking_rv.adapter = adapter
    }


}
