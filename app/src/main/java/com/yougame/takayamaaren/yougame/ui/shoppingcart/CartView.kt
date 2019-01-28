package com.yougame.takayamaaren.yougame.ui.shoppingcart

import com.yougame.takayamaaren.yougame.ui.base.View

interface CartView : View {
    fun onLoadCartItemComplete(items: List<ShoppingCartItem>)
}