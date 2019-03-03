package com.yougame.takayamaaren.yougame.utils

import java.text.SimpleDateFormat
import java.util.*

object DateUtils {
    @JvmStatic
    fun toDateString(date: Date) : String {
        val format = SimpleDateFormat("yyyy-MM-dd hh:mm")
        return format.format(date)
    }
}