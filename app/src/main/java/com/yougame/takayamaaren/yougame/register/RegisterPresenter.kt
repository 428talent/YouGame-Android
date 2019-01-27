package com.yougame.takayamaaren.yougame.register

interface RegisterPresenter {
    /**
     * 创建用户
     * @author takayamaaren
     * @param username 用户名
     * @param password 密码
     */
    fun createUser(username : String,password : String)
}