package com.yougame.takayamaaren.yougame.ui.login

import com.yougame.takayamaaren.yougame.manager.user.Profile
import com.yougame.takayamaaren.yougame.manager.user.UserManager
import com.yougame.takayamaaren.yougame.sdk.ApiError
import com.yougame.takayamaaren.yougame.services.user.ProfileQueryBuilder
import com.yougame.takayamaaren.yougame.services.user.UserServices
import kotlinx.coroutines.GlobalScope
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
                val profileList = ProfileQueryBuilder().inUser(response.payload.userId).inPage(1, 1).query()
                if (profileList.count == 0) {
                    view.showSnackBar("获取用户资料失败")
                }
                UserManager.onLogin(response, username, Profile(profileList.result.first()))

            } catch (e: ApiError) {
                e.printStackTrace()
                view.showSnackBar(e.detail)
            }
        }
    }
}