package com.conqueror.klogindemo.model

import android.provider.ContactsContract
import com.conqueror.klogindemo.presenter.ILoginPresenter

/**
 * Created by yijian2033 on 2017/12/20.
 * 登录注册
 */
interface ILoginModel {

    fun login(onLoginListener: ILoginPresenter.OnLoginListener, userName: String, password: String)

    fun register(onRegisterListener: ILoginPresenter.OnRegisterListener, userName: String, password: String, email: String)

}