package com.yougame.takayamaaren.yougame.ui.login

import com.yougame.takayamaaren.yougame.manager.user.UserManager
import com.yougame.takayamaaren.yougame.sdk.ApiError
import com.yougame.takayamaaren.yougame.services.UserServices
import com.yougame.takayamaaren.yougame.ui.base.View
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class LoginPresenterImpl : LoginPresenter {
    lateinit var view: LoginView
    override fun onAttach(view: LoginView) {
        this.view = view
    }

    override fun onDetach() {

    }

    override fun login(username: String, password: String) {
        GlobalScope.launch {
            try {
                val response = UserServices.login(username, password)
                UserManager.onLogin(response, username)
            } catch (e: ApiError) {
                e.printStackTrace()
                view.showSnackBar(e.detail)
            }
        }
    }
}