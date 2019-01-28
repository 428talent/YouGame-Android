package com.yougame.takayamaaren.yougame.ui.register

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Patterns
import android.view.View
import com.yougame.takayamaaren.yougame.R
import kotlinx.android.synthetic.main.activity_register.*
import kotlinx.android.synthetic.main.content_register.*
import org.jetbrains.anko.sdk27.coroutines.onClick
import org.jetbrains.anko.toast

class RegisterActivity : AppCompatActivity(), RegisterView {
    private val presenter: RegisterPresenter = RegisterPresenterImpl(this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_register)
        title = "注册"
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        toolbar.setNavigationOnClickListener {
            onBackPressed()
        }

        register_btn.onClick {
            takeIf { validate() }?.let {
                val username = username_et.text.toString()
                val password = password_et.text.toString()
                progress_bar.visibility = View.VISIBLE
                presenter.createUser(username, password)
            }
        }


    }

    private fun validate(): Boolean {
        val username = username_et.text.toString()
        takeIf { username.isBlank() }?.let {
            username_layout.error = "未输入用户名"
            username_layout.isErrorEnabled = true
            return false
        }
        val email = email_et.text.toString()
        if (email.isBlank()) {
            email_layout.error = "未输入邮箱"
            email_layout.isErrorEnabled = true
            return false
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            email_layout.error = "邮箱格式错误"
            email_layout.isErrorEnabled = true
            return false
        }
        val password = password_et.text.toString()
        takeIf { password.isBlank() }?.let {
            password_layout.error = "未输入密码"
            password_layout.isErrorEnabled = true
            return false
        }
        val repassword = repassword_et.text.toString()
        takeIf { repassword.isBlank() }?.let {
            repassword_layout.error = "未输入确认密码"
            repassword_layout.isErrorEnabled = true
            return false
        }
        takeIf { password != repassword }?.let {
            repassword_layout.error = "确认密码不一致"
            repassword_layout.isErrorEnabled = true
            return false
        }
        return true

    }

    override fun onRegisterSucceed() {
        toast("注册成功").show()
        progress_bar.visibility = View.GONE
    }

    override fun onRegisterFail() {
        toast("注册失败").show()
        progress_bar.visibility = View.GONE
    }

}
