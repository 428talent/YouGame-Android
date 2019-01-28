package com.yougame.takayamaaren.yougame.manager.user

import com.yougame.takayamaaren.yougame.manager.BoxStoreManager
import com.yougame.takayamaaren.yougame.sdk.model.response.AuthResponseBody
import io.objectbox.Box


object UserManager {
    var user: User? = null
    private lateinit var store: Box<User>
    fun loadUser() {
        store = BoxStoreManager.store.boxFor(User::class.java)
        user = store.all.firstOrNull()
    }

    fun onLogin(response: AuthResponseBody, username: String) {
        user = User(
                uid = response.payload.userId,
                sign = response.payload.sign,
                username = username
        )
        store.removeAll()
        store.put(user!!)

    }
}