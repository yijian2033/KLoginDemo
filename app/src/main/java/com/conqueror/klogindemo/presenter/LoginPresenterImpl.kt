package com.conqueror.klogindemo.presenter

import com.conqueror.klogindemo.bean.LoginResponse
import com.conqueror.klogindemo.bean.RegisterResponse
import com.conqueror.klogindemo.model.ILoginModel
import com.conqueror.klogindemo.model.LoginModelImpl
import com.conqueror.klogindemo.view.viewI.ILoginView

/**
 * Created by yijian2033 on 2017/12/20.
 * TODO
 */
class LoginPresenterImpl(val loginview: ILoginView) : ILoginPresenter, ILoginPresenter.OnLoginListener, ILoginPresenter.OnRegisterListener {

    val mLoginModel: ILoginModel

    init {
        mLoginModel = LoginModelImpl()
    }

    override fun login(username: String, password: String) {
        mLoginModel.login(this, username, password)
    }

    override fun register(username: String, password: String, email: String) {
        mLoginModel.register(this, username, password, email)
    }

    override fun loginSuccess(result: LoginResponse) {
        loginview.loginSuccess(result)
    }

    override fun loginFailed(message: String?) {
        loginview.loginFailed(message)
    }

    override fun registerSuccess(result: RegisterResponse) {
        loginview.registerSuccess(result)
    }

    override fun registerFailed(message: String?) {
        loginview.registerFailed(message)
    }


}