package com.conqueror.klogindemo.view

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Toast
import cn.pedant.SweetAlert.SweetAlertDialog
import com.conqueror.klogindemo.R
import com.conqueror.klogindemo.bean.LoginResponse
import com.conqueror.klogindemo.bean.RegisterResponse
import com.conqueror.klogindemo.presenter.ILoginPresenter
import com.conqueror.klogindemo.presenter.LoginPresenterImpl
import com.conqueror.klogindemo.view.viewI.ILoginView
import kotlinx.android.synthetic.main.activity_main.*
import java.util.regex.Pattern

class LoginActivity : AppCompatActivity(), ILoginView, View.OnClickListener {


    var loginPresenter: ILoginPresenter? = null
    var dialog: SweetAlertDialog? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        loginPresenter = LoginPresenterImpl(this)
        login.setOnClickListener(this)
        register.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v) {
            login -> {
                if (checkContent(true)) {
//                    dialog = SweetAlertDialog(this, SweetAlertDialog.PROGRESS_TYPE)
//                            .setTitleText("正在登录...")
//                    dialog?.setCancelable(false)
//                    dialog?.show()
                    Toast.makeText(this, "正在登陆...", Toast.LENGTH_SHORT).show()
                    loginPresenter?.login(username.text.toString(), password.text.toString())
                }
            }
            register -> {
                if (checkContent(false)) {
//                    dialog = SweetAlertDialog(this, SweetAlertDialog.PROGRESS_TYPE)
//                            .setTitleText("正在注册...")
//                    dialog?.setCancelable(false)
//                    dialog?.show()
                    Toast.makeText(this, "正在注册...", Toast.LENGTH_SHORT).show()
                    loginPresenter?.register(username.text.toString(), password.text.toString(), email.text.toString())
                }
            }
        }
    }


    override fun loginSuccess(result: LoginResponse) {
//        dialog?.changeAlertType(SweetAlertDialog.SUCCESS_TYPE)
//        dialog?.titleText=result.msg
        Toast.makeText(this, "login success:" + result.msg, Toast.LENGTH_SHORT).show()
    }


    override fun loginFailed(message: String?) {
//        dialog?.changeAlertType(SweetAlertDialog.ERROR_TYPE)
//        dialog?.titleText = message
        Toast.makeText(this, "login failed:" + message, Toast.LENGTH_SHORT).show()
    }

    override fun registerSuccess(result: RegisterResponse) {
//        dialog?.changeAlertType(SweetAlertDialog.SUCCESS_TYPE)
//        dialog?.titleText = result.msg
        Toast.makeText(this, "register success:" + result.msg, Toast.LENGTH_SHORT).show()
    }

    override fun registerFailed(message: String?) {
//        dialog?.changeAlertType(SweetAlertDialog.ERROR_TYPE)
//        dialog?.titleText = message
        Toast.makeText(this, "register failed:" + message, Toast.LENGTH_SHORT).show()
    }


    private fun checkContent(login: Boolean): Boolean {
        username.error = null
        password.error = null

        var cancel = false
        var focusView: View? = null

        if (!login) {
            if (TextUtils.isEmpty(email.text.toString())) {
                email.error = "Email不能为空"
                focusView = email
                cancel = true
            } else if (!isEmail(email.text.toString())) {
                email.error = "Email格式不正确"
                focusView = email
                cancel = true
            }
        }

        if (TextUtils.isEmpty(password.text.toString())) {
            password.error = "密码不能为空"
            focusView = password
            cancel = true
        } else if (password.text.length < 6) {
            password.error = "密码长度不能小于6位"
            focusView = password
            cancel = true
        }

        if (TextUtils.isEmpty(username.text.toString())) {
            username.error = "用户名不能为空"
            focusView = username
            cancel = true
        }

        if (cancel) {
            if (focusView != null) {
                focusView.requestFocus()
            }
        } else {
            return true
        }
        return false

    }

    /**
     * 判断email地址
     * @param email
     * @return boolean
     */
    fun isEmail(email: String): Boolean {
        if (email == null) {
            return false
        }
        val regex = "\\w+(\\.\\w)*@\\w+(\\.\\w{2,3}){1,3}"
        val pattern = Pattern.compile(regex)
        return pattern.matcher(email).matches()
    }

}
