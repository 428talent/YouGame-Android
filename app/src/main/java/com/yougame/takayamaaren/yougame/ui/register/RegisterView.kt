package com.yougame.takayamaaren.yougame.ui.register

interface RegisterView {
    /**
     * 注册用户成功时的回调
     */
    fun onRegisterSucceed()

    /**
     * 注册失败时的回调
     */
    fun onRegisterFail()
}