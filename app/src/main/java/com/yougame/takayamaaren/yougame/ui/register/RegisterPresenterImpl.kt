package com.yougame.takayamaaren.yougame.ui.register

import com.yougame.takayamaaren.yougame.services.user.UserServices
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.HttpException


class RegisterPresenterImpl(val view: RegisterView) : RegisterPresenter {
    override fun createUser(username: String, password: String) {
        GlobalScope.launch (Dispatchers.Main) {
            try {
                val user = UserServices.registerUser(username, password, "986486507@qq.com")
            } catch (e: HttpException) {
                e.response().errorBody()
            }
        }

    }
}