package com.iame.qnnect.android.src.splash

import android.app.Activity
import android.content.Intent
import android.graphics.Color
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.WindowManager
import android.widget.Toast
import androidx.lifecycle.Observer
import com.google.android.material.snackbar.Snackbar
import com.google.android.play.core.appupdate.AppUpdateInfo
import com.google.android.play.core.appupdate.AppUpdateManager
import com.google.android.play.core.appupdate.AppUpdateManagerFactory
import com.google.android.play.core.install.InstallState
import com.google.android.play.core.install.model.AppUpdateType
import com.google.android.play.core.install.model.UpdateAvailability
import com.google.android.play.core.tasks.Task
import com.iame.qnnect.android.MyApplication.Companion.sSharedPreferences
import com.iame.qnnect.android.R
import com.iame.qnnect.android.base.BaseActivity
import com.iame.qnnect.android.databinding.ActivitySplashBinding
import com.iame.qnnect.android.src.invite.InviteActivity
import com.iame.qnnect.android.src.login.LoginActivity
import com.iame.qnnect.android.src.main.MainActivity
import com.iame.qnnect.android.src.onboarding.OnboardActivity
import com.iame.qnnect.android.src.profile.ProfileActivity
import com.iame.qnnect.android.src.splash.model.PostRefreshRequest
import com.iame.qnnect.android.viewmodel.SplashViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.lang.Exception
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

class SplashActivity : BaseActivity<ActivitySplashBinding, SplashViewModel>() {
    override val layoutResourceId: Int
        get() = R.layout.activity_splash // get() : 커스텀 접근자, 코틀린 문법

    override val viewModel: SplashViewModel by viewModel()

    var cafeCode = "null"

    var REQUEST_CODE_UPDATE = 366

    // 앱 업데이트 매니저 초기화
//    private lateinit var appUpdateManager: AppUpdateManager

    override fun initStartView() {
    }

    override fun initDataBinding() {
        viewModel.refreshResponse.observe(this, Observer {
            if (it.accessToken == "" || it.refreshToken == "") {
                Handler(Looper.getMainLooper()).postDelayed({
                    startActivity(Intent(this, LoginActivity::class.java))
                    finish()
                }, 1500)
            } else {
                Log.d("login_response", it.toString())
                baseToken.setAccessToken(this, it.accessToken, it.refreshToken)
                Handler(Looper.getMainLooper()).postDelayed({
                    var intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                }, 1500)
            }
        })
    }

    override fun initAfterBinding() {
//        appUpdateManager = AppUpdateManagerFactory.create(this)
//
//        CoroutineScope(Dispatchers.Main).launch {
//            // 업데이트를 체크하는데 사용되는 인텐트를 리턴한다.
//            val appUpdateInfo = appUpdateManager.appUpdateInfo.await()
//
//            when (appUpdateInfo.updateAvailability()) {
//                UpdateAvailability.UPDATE_AVAILABLE -> {
//                    //업데이트 가능한 상태
//                    requestUpdate(appUpdateInfo)
//                }
//            }
//        }
    }

    override fun onResume() {
        super.onResume()
//        appUpdateManager
//            .appUpdateInfo
//            .addOnSuccessListener { appUpdateInfo: AppUpdateInfo ->
//                if (appUpdateInfo.updateAvailability()
//                    == UpdateAvailability.DEVELOPER_TRIGGERED_UPDATE_IN_PROGRESS
//                ) {
//                    // If an in-app update is already running, resume the update.
//                    try {
//                        appUpdateManager.startUpdateFlowForResult(
//                            appUpdateInfo,
//                            AppUpdateType.IMMEDIATE,
//                            this,
//                            REQUEST_CODE_UPDATE)
//                    } catch (e: Exception) {
//                        e.printStackTrace()
//                    }
//                }
//            }

        if (intent.action == Intent.ACTION_VIEW) {
            cafeCode = intent.data!!.getQueryParameter("code")!!
            baseToken.setCafeCode(this, cafeCode)
        }

        val jwtToken: String? = sSharedPreferences.getString("X-ACCESS-TOKEN", null)
        val refreshToken: String? = sSharedPreferences.getString("refresh-token", null)

        if (jwtToken != null) {
            var refreshRequest = PostRefreshRequest(jwtToken, refreshToken!!)
            viewModel.postRefresh(refreshRequest)
        } else {
            Handler(Looper.getMainLooper()).postDelayed({
                startActivity(Intent(this, OnboardActivity::class.java))
                finish()
            }, 1500)
        }
    }

//    private fun requestUpdate(appUpdateInfo: AppUpdateInfo) {
//        try {
//            appUpdateManager.startUpdateFlowForResult( // 'getAppUpdateInfo()' 에 의해 리턴된 인텐트
//                appUpdateInfo,  // 'AppUpdateType.FLEXIBLE': 사용자에게 업데이트 여부를 물은 후 업데이트 실행 가능
//                // 'AppUpdateType.IMMEDIATE': 사용자가 수락해야만 하는 업데이트 창을 보여줌
//                AppUpdateType.IMMEDIATE,  // 현재 업데이트 요청을 만든 액티비티, 여기선 MainActivity.
//                this,  // onActivityResult 에서 사용될 REQUEST_CODE.
//                REQUEST_CODE_UPDATE)
//        } catch (e: Exception) {
//            e.printStackTrace()
//        }
//    }

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

    // 업데이트 결과
//    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        super.onActivityResult(requestCode, resultCode, data)
//
//        if (requestCode == REQUEST_CODE_UPDATE) {
//            if (resultCode != Activity.RESULT_OK) {
//                Toast.makeText(this, "업데이트 다운로드 취소", Toast.LENGTH_SHORT).show()
//
//                val appUpdateManager = AppUpdateManagerFactory.create(applicationContext)
//                val appUpdateInfoTask: Task<AppUpdateInfo> = appUpdateManager.appUpdateInfo
//                appUpdateInfoTask.addOnSuccessListener { appUpdateInfo: AppUpdateInfo ->
//                    if (appUpdateInfo.updateAvailability() == UpdateAvailability.UPDATE_AVAILABLE // flexible한 업데이트를 위해서는 AppUpdateType.FLEXIBLE을 사용한다.
//                        && appUpdateInfo.isUpdateTypeAllowed(AppUpdateType.IMMEDIATE)
//                    ) {
//                        // 업데이트를 체크하는데 사용되는 인텐트를 리턴한다.
//                        val appUpdateInfoTask = appUpdateManager.appUpdateInfo
//
//                        appUpdateInfoTask.addOnSuccessListener { appUpdateInfo: AppUpdateInfo ->
//                            if (appUpdateInfo.updateAvailability() == UpdateAvailability.UPDATE_AVAILABLE // flexible한 업데이트를 위해서는 AppUpdateType.FLEXIBLE을 사용한다.
//                                && appUpdateInfo.isUpdateTypeAllowed(AppUpdateType.IMMEDIATE)
//                            ) {
//                                // 업데이트를 다시 요청한다.
//                                requestUpdate(appUpdateInfo)
//                            }
//                        }
//                    }
//                }
//            }
//        }
//    }
}


