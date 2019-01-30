package com.yougame.takayamaaren.yougame.utils

object Rating {
    fun GetRatingText(avgRating: Double): String {
        if (avgRating < 2) {
            return "多半差评"
        }
        if (avgRating < 2.5) {
            return "评价不佳"
        }
        if (avgRating < 3.5) {
            return "中规中矩"
        }
        if (avgRating < 4.5) {
            return "评价不错"
        }
        if (avgRating <= 5) {
            return "多数好评"
        }
        return "无数据"
    }
}