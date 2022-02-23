package com.iame.qnnect.android.base

import com.iame.qnnect.android.src.login.service.LoginAPI
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import what.the.mvvm.MyConstant.Companion.BASE_URL

object RetrofitInstance {
    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val loginAPI : LoginAPI by lazy {
        retrofit.create(LoginAPI::class.java)
    }
}