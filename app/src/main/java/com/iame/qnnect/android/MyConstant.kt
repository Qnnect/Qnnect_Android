package com.iame.qnnect.android

import android.content.SharedPreferences
import retrofit2.Retrofit

class MyConstant {
    // 코틀린의 전역변수 문법
    companion object {
        const val BASE_URL = "http://qnnect.shop:8080"
        const val KAKAO_APP_KEY = "80ae4d5ffffa5528fd8dbf7c29b8a015"
        const val radius = 16
    }
}