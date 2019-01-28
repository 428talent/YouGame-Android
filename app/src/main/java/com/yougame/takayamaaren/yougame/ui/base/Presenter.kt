package com.yougame.takayamaaren.yougame.ui.base

interface Presenter<T : View> {
    fun onAttach(view: T)
    fun onDetach()
}