package com.conqueror.klogindemo.presenter

import com.conqueror.klogindemo.bean.LoginResponse
import com.conqueror.klogindemo.bean.RegisterResponse

/**
 * Created by yijian2033 on 2017/12/20.
 * TODO
 */
interface ILoginPresenter {

    /**
     * 登录
     */
    fun login(username: String, password: String)

    /**
     * 登录的接口
     */
    interface OnLoginListener {

        fun loginSuccess(result: LoginResponse)

        fun loginFailed(message: String?)
    }


    /**
     * 注册
     */
    fun register(username: String, password: String, email: String)

    /**
     * 注册的接口
     */
    interface OnRegisterListener {

        fun registerSuccess(result: RegisterResponse)

        fun registerFailed(message: String?)
    }

}