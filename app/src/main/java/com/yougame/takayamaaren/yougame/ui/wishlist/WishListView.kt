package com.yougame.takayamaaren.yougame.ui.wishlist

import com.yougame.takayamaaren.yougame.sdk.model.response.Game
import com.yougame.takayamaaren.yougame.sdk.model.response.WishListItem
import com.yougame.takayamaaren.yougame.ui.base.View

interface WishListView : View {
    data class WishListItemModel(
            val item: WishListItem,
            val game: Game,
            val cover: String
    )

    fun onWishListLoad(items: List<WishListItemModel>)
}