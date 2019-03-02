package com.yougame.takayamaaren.yougame.ui.main.hot

import com.yougame.takayamaaren.yougame.sdk.model.response.GameCollection
import com.yougame.takayamaaren.yougame.ui.base.View

interface HotView : View {
    fun applyGameCollections(list: List<GameCollection>)
}