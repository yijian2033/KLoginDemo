package com.conqueror.klogindemo.api

import com.conqueror.klogindemo.bean.LoginResponse
import com.conqueror.klogindemo.bean.RegisterResponse
import com.conqueror.klogindemo.constant.Constant
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query


interface RetrofitService {

    /**
     * 注册
     * key 用户申请的appkey
     * username 用户名（一个key只能存在唯一username）
     * password 用户密码（建议加密）
     * email 邮箱
     */

    @GET("register")
    fun userRegister(
            @Query("username") username: String,
            @Query("password") password: String,
            @Query("email") email: String,
            @Query("key") key: String = Constant.KEY

    ): Observable<RegisterResponse>


    @GET("login")
    fun userLogin(@Query("username") username: String,
                  @Query("password") password: String,
                  @Query("key") key: String = Constant.KEY
    ): Observable<LoginResponse>

}