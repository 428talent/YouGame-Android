package com.yougame.takayamaaren.yougame.services.base

import com.yougame.takayamaaren.yougame.services.game.GameQueryBuilder

abstract class BaseQueryBuilder<T> {
    protected val ids = mutableListOf<Int>()
    protected var page = 1
    protected var pageSize = 10

    fun inId(vararg ids: Int): T {
        this.ids.addAll(ids.toList())
        return getThis()
    }

    fun inId(ids: List<Int>): T {
        this.ids.addAll(ids)
        return getThis()
    }

    fun inPage(page: Int, pageSize: Int): T {
        this.page = page
        this.pageSize = pageSize
        return getThis()
    }

    abstract fun getThis(): T;
}