package com.yougame.takayamaaren.yougame.extension

import android.support.design.widget.TextInputLayout

fun TextInputLayout.showError(message: String) {
    this.error = message
    this.isErrorEnabled = true
}