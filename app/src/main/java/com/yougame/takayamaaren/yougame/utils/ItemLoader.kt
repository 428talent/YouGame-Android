package com.yougame.takayamaaren.yougame.utils

import com.yougame.takayamaaren.yougame.sdk.model.response.Container
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

abstract class ItemLoader<T> {
    private var hasMore = true
    private var page = 1
    lateinit var onLoadData: (list: List<T>) -> Unit
    lateinit var onLoadEnd: () -> Unit
    lateinit var onLoadMore: (list: List<T>) -> Unit
    abstract suspend fun queryData(page: Int, pageSize: Int): Container<T>
    fun loadMore() {
        if (!hasMore) {
            return
        }
        page += 1
        GlobalScope.launch(Dispatchers.IO) {
            val result = queryData(page, 10)
            onLoadMore(result.result)
            checkHasMore(result.nextPage)
        }
    }

    private fun checkHasMore(nextPage: String?) {
        hasMore = !nextPage.isNullOrEmpty()
        if (!hasMore) {
            onLoadEnd()
        }
    }

    fun loadData() {
        GlobalScope.launch(Dispatchers.IO) {
            val result = queryData(page, 10)
            onLoadData(result.result)
            checkHasMore(result.nextPage)
        }
    }


}