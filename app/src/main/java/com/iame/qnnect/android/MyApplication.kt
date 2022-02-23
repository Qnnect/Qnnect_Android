package com.iame.qnnect.android

import android.app.Application
import com.iame.qnnect.android.di.myDiModule
import com.kakao.sdk.common.KakaoSdk
import org.koin.android.ext.android.startKoin

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin(applicationContext, myDiModule)

        // Kakao Sdk 초기화
        KakaoSdk.init(this, "80ae4d5ffffa5528fd8dbf7c29b8a015")
    }
}