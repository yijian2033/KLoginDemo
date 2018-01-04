package com.conqueror.klogindemo.bean

/**
 * Created by yijian2033 on 2017/12/20.
 * 登录返回result
 * token	用户登录生成的token
 * uid	用户Id
*/
data class LoginResultResponse(val token: String, val uid: String)