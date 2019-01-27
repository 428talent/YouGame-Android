package com.yougame.takayamaaren.yougame.register

import com.yougame.takayamaaren.yougame.sdk.ApiService
import com.yougame.takayamaaren.yougame.sdk.model.request.CreateUserRequest
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


class RegisterPresenterImpl(val view: RegisterView) : RegisterPresenter {
    override fun createUser(username: String, password: String) {
        ApiService.api.createUser(CreateUserRequest(username, password))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe({ response ->
                    takeIf { response.result }?.let {
                        view.onRegisterSucceed()
                    }?.let { view.onRegisterFail() }
                }, { e ->
                    e.printStackTrace()
                    view.onRegisterFail()
                })
    }
}