package com.conqueror.klogindemo.view.viewI

import com.conqueror.klogindemo.bean.LoginResponse
import com.conqueror.klogindemo.bean.RegisterResponse

/**
 * Created by yijian2033 on 2017/12/20.
 * 登录和注册的状态
 */
interface ILoginView {

    fun loginSuccess(result: LoginResponse)

    fun loginFailed(message: String?)

    fun registerSuccess(result: RegisterResponse)

    fun registerFailed(message: String?)

}