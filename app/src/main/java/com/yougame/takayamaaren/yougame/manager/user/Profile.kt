package com.yougame.takayamaaren.yougame.manager.user

import com.yougame.takayamaaren.yougame.sdk.model.response.Profile
import com.yougame.takayamaaren.yougame.utils.AppConfig
import io.objectbox.annotation.Entity
import io.objectbox.annotation.Id

@Entity
data class Profile(
        @Id
        var id: Long = 0,
        var avatar: String = "",
        var email: String = "",
        var nickname: String = "",
        var updateAt: String = "",
        var userId: Int = 0
) {
    constructor(response: Profile) : this(
            avatar = "${AppConfig.ApiServerUrl}${response.avatar}",
            email = response.email,
            nickname = response.nickname,
            updateAt = response.updateAt,
            userId = response.userId
    )
}