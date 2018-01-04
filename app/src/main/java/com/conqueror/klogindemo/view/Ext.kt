package com.conqueror.klogindemo.view

import android.util.Log

/**
 * Created by yijian2033 on 2017/12/20.
 * TODO
 */
fun loge(tag: String, content: String?) {
    Log.e(tag, content ?: tag)
}