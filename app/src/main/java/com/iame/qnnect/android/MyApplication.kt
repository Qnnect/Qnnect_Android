package com.iame.qnnect.android

import android.app.Application
import android.content.Intent
import android.content.SharedPreferences
import android.provider.Settings
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate
import androidx.test.core.app.ActivityScenario.launch
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.play.core.appupdate.AppUpdateInfo
import com.google.android.play.core.appupdate.AppUpdateManagerFactory
import com.google.android.play.core.install.model.AppUpdateType
import com.google.android.play.core.install.model.UpdateAvailability
import com.google.android.play.core.tasks.Task
import com.google.firebase.messaging.FirebaseMessaging
import com.iame.qnnect.android.MyConstant.Companion.BASE_URL
import com.iame.qnnect.android.base.BaseToken
import com.iame.qnnect.android.base.NullOnEmptyConverterFactory
import com.iame.qnnect.android.base.XAccessTokenInterceptor
import com.iame.qnnect.android.di.BearerInterceptor
import com.iame.qnnect.android.di.myDiModule
import com.iame.qnnect.android.src.alarm.AlarmActivity
import com.iame.qnnect.android.src.reply.service.PostReplyAPI
import com.kakao.sdk.common.KakaoSdk
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.android.startKoin
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

class MyApplication : Application() {

    // 코틀린의 전역변수 문법
    companion object {
        // 만들어져있는 SharedPreferences 를 사용해야합니다. 재생성하지 않도록 유념해주세요
        lateinit var sSharedPreferences: SharedPreferences
        lateinit var editor: SharedPreferences.Editor

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

        // 다크모드 비활성화
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

        // Kakao Sdk 초기화
        KakaoSdk.init(this, "80ae4d5ffffa5528fd8dbf7c29b8a015")

        sSharedPreferences =
            applicationContext.getSharedPreferences("Qnnect", MODE_PRIVATE)

        editor = sSharedPreferences.edit()

        // 레트로핏 인스턴스 생성
        initRetrofitInstance()

        // fire base settings
        FirebaseMessaging.getInstance().token.addOnCompleteListener(OnCompleteListener { task ->
            if (!task.isSuccessful) {
                Log.w("response!", "Fetching FCM registration token failed", task.exception)
                return@OnCompleteListener
            }

            // Get new FCM registration token
            val token = task.result
            task.result

            Log.d("fcm_response!", token!!)
            var baseToken = BaseToken()
            baseToken.setFCM(this, token)

            Log.d("fcm_response", "MyApplication "+baseToken.getAlarm(this))
        })

        // 자동 업데이트
//        val appUpdateManager = AppUpdateManagerFactory.create(this)
//
//        appUpdateManager?.let {
//            it.appUpdateInfo.addOnSuccessListener { appUpdateInfo ->
//
//                if (appUpdateInfo.updateAvailability() == UpdateAvailability.UPDATE_AVAILABLE
//                    && appUpdateInfo.isUpdateTypeAllowed(AppUpdateType.IMMEDIATE)) {
//                    // or AppUpdateType.FLEXIBLE
//                    appUpdateManager?.startUpdateFlowForResult(
//                        appUpdateInfo,
//                        AppUpdateType.IMMEDIATE, // or AppUpdateType.FLEXIBLE
//                        this,
//                        REQUEST_CODE_UPDATE
//                    )
//                }
//            }
//        }
    }

    // 레트로핏 인스턴스를 생성하고, 레트로핏에 각종 설정값들을 지정해줍니다.
    // 연결 타임아웃시간은 5초로 지정이 되어있고, HttpLoggingInterceptor를 붙여서 어떤 요청이 나가고 들어오는지를 보여줍니다.
    private fun initRetrofitInstance() {
        val client: OkHttpClient = OkHttpClient.Builder()
            .readTimeout(5000, TimeUnit.MILLISECONDS)
            .connectTimeout(5000, TimeUnit.MILLISECONDS)
            // 로그캣에 okhttp.OkHttpClient로 검색하면 http 통신 내용을 보여줍니다.
            .addInterceptor(BearerInterceptor())
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .addNetworkInterceptor(XAccessTokenInterceptor()) // JWT 자동 헤더 전송
            .build()

        // sRetrofit 이라는 전역변수에 API url, 인터셉터, Gson을 넣어주고 빌드해주는 코드
        // 이 전역변수로 http 요청을 서버로 보내면 됩니다.
        sRetrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(NullOnEmptyConverterFactory())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    // auto update
//    suspend fun Task<AppUpdateInfo>.await(): AppUpdateInfo {
//        return suspendCoroutine { continuation ->
//            addOnCompleteListener { result ->
//                if (result.isSuccessful) {
//                    continuation.resume(result.result)
//                } else {
//                    continuation.resumeWithException(result.exception)
//                }
//            }
//        }
//    }
}