package com.iame.qnnect.android

import android.app.Application
import android.content.SharedPreferences
import com.iame.qnnect.android.di.myDiModule
import com.kakao.sdk.common.KakaoSdk
import org.koin.android.ext.android.startKoin
import retrofit2.Retrofit

class MyApplication : Application() {

    // 코틀린의 전역변수 문법
    companion object {
        // 만들어져있는 SharedPreferences 를 사용해야합니다. 재생성하지 않도록 유념해주세요
        lateinit var sSharedPreferences: SharedPreferences

        // JWT Token Header 키 값
        val X_ACCESS_TOKEN = "X-ACCESS-TOKEN"

        // refresh_token
        val refresh_token = "refresh-token"

        // Retrofit 인스턴스, 앱 실행시 한번만 생성하여 사용합니다.
        lateinit var sRetrofit: Retrofit
    }

    override fun onCreate() {
        super.onCreate()
        startKoin(applicationContext, myDiModule)

        // Kakao Sdk 초기화
        KakaoSdk.init(this, "80ae4d5ffffa5528fd8dbf7c29b8a015")

        sSharedPreferences =
            applicationContext.getSharedPreferences("Qnnect", MODE_PRIVATE)
    }
}