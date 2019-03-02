package com.yougame.takayamaaren.yougame.ui.main.hot

import com.yougame.takayamaaren.yougame.services.collection.GameCollectionQueryBuilder
import com.yougame.takayamaaren.yougame.ui.base.Presenter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

interface HotPresenter : Presenter<HotView> {
    fun  loadNewGameAndHotGameCollection()
}

class HotViewPresenterImpl : HotPresenter {


    lateinit var view: HotView
    override fun onAttach(view: HotView) {
        this.view = view
    }

    override fun onDetach() {

    }

    override fun loadNewGameAndHotGameCollection() {
        GlobalScope.launch(Dispatchers.IO) {
            val gameCollectionsResponse = GameCollectionQueryBuilder().withName("newgame","recommend").query()
            launch(Dispatchers.Main) {
                view.applyGameCollections(gameCollectionsResponse.result)
            }

        }
    }
}