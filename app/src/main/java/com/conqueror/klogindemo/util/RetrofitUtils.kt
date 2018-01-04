package com.conqueror.klogindemo.util

import com.conqueror.klogindemo.api.RetrofitService
import com.conqueror.klogindemo.constant.Constant
import com.conqueror.klogindemo.view.loge
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Created by yijian2033 on 2017/12/21.
 * retrofit的创建和请求
 */
class RetrofitUtils<T> {

    companion object {
        /**
         * 创建retrofit
         */
        fun create(url: String): Retrofit {
            //日志显示级别
            val level: HttpLoggingInterceptor.Level = HttpLoggingInterceptor.Level.BODY
            //新建Log拦截器
            val loggingIterceptor: HttpLoggingInterceptor = HttpLoggingInterceptor(HttpLoggingInterceptor.Logger { message ->
                loge("RetrofitUtils", "OkHttp:" + message)
            })
            loggingIterceptor.level = level
            //OKHttpClientBuilder
            val okHttpClientBuilder = OkHttpClient().newBuilder()
            okHttpClientBuilder.connectTimeout(60, TimeUnit.SECONDS)
            okHttpClientBuilder.readTimeout(10, TimeUnit.SECONDS)
            //OKHttp进行添加拦截器loggingInterceptor
//            okHttpClientBuilder.addInterceptor(loggingIterceptor)

            return Retrofit.Builder()
                    .baseUrl(url)
                    .client(okHttpClientBuilder.build())
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build()

        }

        /**
         * 获取serviceApi
         */
        fun <T> getService(url: String, service: Class<T>): T {
            return create(url).create(service)
        }

        val retrofitService: RetrofitService = RetrofitUtils.getService(Constant.REQUEST_BASE_URL, RetrofitService::class.java)

    }

}