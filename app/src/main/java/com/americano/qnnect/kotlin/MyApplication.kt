package com.americano.qnnect.kotlin

import android.app.Application
import com.americano.qnnect.kotlin.di.myDiModule
import com.kakao.sdk.common.KakaoSdk
import org.koin.android.ext.android.startKoin

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin(applicationContext, myDiModule)

        // Kakao Sdk 초기화
        KakaoSdk.init(this, "113796b4328948f2a242ab91a2fa24db")
    }
}