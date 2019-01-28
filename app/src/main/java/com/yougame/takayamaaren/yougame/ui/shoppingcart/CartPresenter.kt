package com.yougame.takayamaaren.yougame.ui.shoppingcart

import com.yougame.takayamaaren.yougame.ui.base.Presenter

interface CartPresenter : Presenter<CartView>{
    fun getWishlistItems()
}