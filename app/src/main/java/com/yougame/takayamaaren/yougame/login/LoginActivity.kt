package com.yougame.takayamaaren.yougame.login

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.yougame.takayamaaren.yougame.R
import com.yougame.takayamaaren.yougame.register.RegisterActivity
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.content_login.*
import org.jetbrains.anko.sdk27.coroutines.onClick
import org.jetbrains.anko.startActivity


class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        title = "登录"
        setSupportActionBar(login_toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        login_toolbar.setNavigationOnClickListener {
            onBackPressed()
        }
        login_reg.onClick {
            startActivity<RegisterActivity>()
        }


    }

}
