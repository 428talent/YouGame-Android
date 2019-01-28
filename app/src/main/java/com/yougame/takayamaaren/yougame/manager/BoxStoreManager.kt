package com.yougame.takayamaaren.yougame.manager

import android.content.Context
import com.yougame.takayamaaren.yougame.manager.user.MyObjectBox
import io.objectbox.BoxStore

object BoxStoreManager {
    lateinit var store: BoxStore
    fun init(context: Context) {
        store = MyObjectBox.builder().androidContext(context).build()
    }
}