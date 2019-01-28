package com.yougame.takayamaaren.yougame.ui.login

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.EditText
import com.mobsandgeeks.saripaar.ValidationError
import com.mobsandgeeks.saripaar.Validator
import com.mobsandgeeks.saripaar.annotation.*
import com.yougame.takayamaaren.yougame.R
import com.yougame.takayamaaren.yougame.extension.showError
import com.yougame.takayamaaren.yougame.ui.register.RegisterActivity
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.content_login.*
import org.jetbrains.anko.design.snackbar
import org.jetbrains.anko.sdk27.coroutines.onClick
import org.jetbrains.anko.startActivity


class LoginActivity : AppCompatActivity(), LoginView, Validator.ValidationListener {


    private val presenter: LoginPresenter = LoginPresenterImpl()


    @NotEmpty(message = "用户名不能为空")
    @Password(min = 6, scheme = Password.Scheme.ANY, message = "密码格式错误")
    private lateinit var passwordEditText: EditText

    @NotEmpty(message = "用户名不能为空")
    @Length(max = 16, min = 6, message = "用户名最大16个字符,至少6个")
    private lateinit var usernameEditText: EditText

    private lateinit var validator: Validator
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        presenter.onAttach(this)
        passwordEditText = password_et
        usernameEditText = username_et

        validator = Validator(this)
        validator.setValidationListener(this)
        title = "登录"
        setSupportActionBar(login_toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        login_toolbar.setNavigationOnClickListener {
            onBackPressed()
        }
        login_reg.onClick {
            startActivity<RegisterActivity>()
        }

        loginBtn.onClick {
            validator.validate()
        }
    }

    override fun showSnackBar(message: String) {
        root.snackbar(message).show()
    }

    override fun onValidationFailed(errors: MutableList<ValidationError>) {
        errors.forEach { err ->
            when (err.view) {
                passwordEditText -> {
                    password_layout.showError(err.getCollatedErrorMessage(this).split("\n").first())
                }
                usernameEditText -> {
                    username_layout.showError(err.getCollatedErrorMessage(this).split("\n").first())
                }
            }
        }
    }

    override fun onValidationSucceeded() {
        presenter.login(username_et.text.toString(), password_et.text.toString())
    }


}
