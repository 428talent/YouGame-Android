package com.yougame.takayamaaren.yougame.services.user

import com.yougame.takayamaaren.yougame.sdk.model.response.Container
import com.yougame.takayamaaren.yougame.sdk.model.response.Profile
import com.yougame.takayamaaren.yougame.services.base.BaseQueryBuilder
import com.yougame.takayamaaren.yougame.ui.clients.ApiClient

class ProfileQueryBuilder : BaseQueryBuilder<ProfileQueryBuilder>() {
    private val userIds = mutableListOf<Int>()
    override fun getThis(): ProfileQueryBuilder {
        return this
    }

    fun inUser(vararg userIds: Int): ProfileQueryBuilder {
        this.userIds.addAll(userIds.toList())
        return this
    }
    fun inUser(userIds: List<Int>): ProfileQueryBuilder {
        this.userIds.addAll(userIds)
        return this
    }

    suspend fun query(): Container<Profile> {
        return ApiClient.client.getProfileList(
                ids,
                userIds,
                mapOf(
                        "page" to page.toString(),
                        "pageSize" to pageSize.toString()
                )
        ).await()
    }
}