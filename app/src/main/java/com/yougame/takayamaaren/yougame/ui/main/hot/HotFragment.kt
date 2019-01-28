package com.yougame.takayamaaren.yougame.ui.main.hot


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.yougame.takayamaaren.yougame.Mock
import com.yougame.takayamaaren.yougame.R
import com.yougame.takayamaaren.yougame.ui.gamelist.GoodListActivity
import com.yougame.takayamaaren.yougame.ui.good.GoodActivity
import kotlinx.android.synthetic.main.fragment_hot.view.*
import org.jetbrains.anko.support.v4.startActivity


class HotFragment : Fragment() {
    private val adapter by lazy {
        HotAdapter(mutableListOf(
                HotMultiItem(HotMultiItem.VIEW_TYPE_HOT_SALE, BaseHotItemData()),
                HotMultiItem(HotMultiItem.VIEW_TYPE_PROMOTIONS,BaseHotItemData()),
                HotMultiItem(HotMultiItem.VIEW_TYPE_GAME_CATEGORY,BaseHotItemData()),
                HotMultiItem(HotMultiItem.VIEW_TYPE_GAME_SET,GameSetData(Mock.ubisoftGame,"来自Ubisoft的游戏")),
                HotMultiItem(HotMultiItem.VIEW_TYPE_GAME_SET,GameSetData(Mock.ubisoftGame,"来自Ubisoft的游戏")),
                HotMultiItem(HotMultiItem.VIEW_TYPE_WEEKLY_SALE,BaseHotItemData()),
                HotMultiItem(HotMultiItem.VIEW_TYPE_RECOMMENDS,RecommendGameData("Cuphead",233.3f)),
                HotMultiItem(HotMultiItem.VIEW_TYPE_RECOMMENDS,RecommendGameData("游戏1",23.3f)),
                HotMultiItem(HotMultiItem.VIEW_TYPE_RECOMMENDS,RecommendGameData("游戏1",23.3f)),
                HotMultiItem(HotMultiItem.VIEW_TYPE_RECOMMENDS,RecommendGameData("游戏1",23.3f)),
                HotMultiItem(HotMultiItem.VIEW_TYPE_RECOMMENDS,RecommendGameData("游戏1",23.3f)),
                HotMultiItem(HotMultiItem.VIEW_TYPE_RECOMMENDS,RecommendGameData("游戏1",23.3f))
        ),activity!!).apply {
            bindToRecyclerView(view?.hot_rv)
            setOnItemClickListener { adapter, view, position ->
                when (data[position].viewType) {
                    HotMultiItem.VIEW_TYPE_PROMOTIONS -> {
                        startActivity<GoodListActivity>()
                    }
                    HotMultiItem.VIEW_TYPE_RECOMMENDS -> {
                        startActivity<GoodActivity>()
                    }
                }
            }
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
        return inflater.inflate(R.layout.fragment_hot, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.hot_rv.adapter = adapter
    }


    companion object {

        @JvmStatic
        fun newInstance(param1: String, param2: String) =
                HotFragment().apply {
                    arguments = Bundle().apply {

                    }
                }
    }
}
