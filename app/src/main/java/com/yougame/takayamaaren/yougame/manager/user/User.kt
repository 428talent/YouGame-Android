package com.yougame.takayamaaren.yougame.manager.user

import io.objectbox.annotation.Entity
import io.objectbox.annotation.Id

@Entity
data class User(
        @Id
        var id: Long = 0,
        var uid: Int = 0,
        var username: String = "",
        var sign: String = ""
)