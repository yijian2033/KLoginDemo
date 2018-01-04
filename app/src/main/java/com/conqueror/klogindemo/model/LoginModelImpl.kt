package com.conqueror.klogindemo.model

import com.conqueror.klogindemo.presenter.ILoginPresenter
import com.conqueror.klogindemo.util.RetrofitUtils
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by yijian2033 on 2017/12/20.
 * TODO
 */
class LoginModelImpl : ILoginModel {

    var mOnLoginListener: ILoginPresenter.OnLoginListener? = null
    var mOnRegisterListener: ILoginPresenter.OnRegisterListener? = null

    override fun login(onLoginListener: ILoginPresenter.OnLoginListener, username: String, password: String) {

        if (mOnLoginListener == null) {
            mOnLoginListener = onLoginListener
        }

        RetrofitUtils
                .retrofitService
                .userLogin(username, password)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { result ->
                            when (result.retCode) {
                                "200" ->
                                    mOnLoginListener?.loginSuccess(result)
                                else ->
                                    mOnLoginListener?.loginFailed(result.msg)

                            }
                        },
                        { error ->
                            mOnLoginListener?.loginFailed(error.message)
                        }
                )


    }

    override fun register(onRegisterListener: ILoginPresenter.OnRegisterListener, username: String, password: String, email: String) {

        if (mOnRegisterListener == null) {
            mOnRegisterListener = onRegisterListener
        }

        RetrofitUtils
                .retrofitService
                .userRegister(username, password, email)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { result ->
                            when (result.retCode) {
                                "200" ->
                                    mOnRegisterListener?.registerSuccess(result)
                                else ->
                                    mOnRegisterListener?.registerFailed(result.msg)
                            }
                        },
                        { error ->
                            mOnRegisterListener?.registerFailed(error.message)
                        }
                )

    }
}