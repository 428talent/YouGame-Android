package com.yougame.takayamaaren.yougame.manager.user

import com.yougame.takayamaaren.yougame.manager.BoxStoreManager
import com.yougame.takayamaaren.yougame.sdk.model.response.AuthResponseBody
import io.objectbox.Box


object UserManager {
    var user: User? = null
    var profile: Profile? = null
    private lateinit var store: Box<User>
    private lateinit var profileStore: Box<Profile>
    fun loadUser() {
        store = BoxStoreManager.store.boxFor(User::class.java)
        profileStore = BoxStoreManager.store.boxFor(Profile::class.java)
        user = store.all.firstOrNull()
        profile = profileStore.all.firstOrNull()
    }

    fun onLogin(response: AuthResponseBody, username: String, profile: Profile) {
        this.user = User(
                uid = response.payload.userId,
                sign = response.payload.sign,
                username = username
        )
        this.profile = profile
        store.removeAll()
        store.put(user!!)
        profileStore.removeAll()
        profileStore.put(profile)

    }
}