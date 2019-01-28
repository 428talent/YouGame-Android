package com.yougame.takayamaaren.yougame.ui.login

import com.yougame.takayamaaren.yougame.ui.base.Presenter

interface LoginPresenter : Presenter<LoginView> {
    fun login(username: String, password: String)
}