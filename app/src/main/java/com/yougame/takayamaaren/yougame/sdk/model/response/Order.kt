package com.yougame.takayamaaren.yougame.sdk.model.response

import java.util.*

data class Order(
        val id: Int,
        val state: String,
        val created: Date
)