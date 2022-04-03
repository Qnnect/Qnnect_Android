package com.iame.qnnect.android.src.splash

import android.content.Intent
import android.content.pm.PackageInfo
import android.net.Uri
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.Observer
import com.google.firebase.dynamiclinks.ktx.dynamicLinks
import com.google.firebase.ktx.Firebase
import com.iame.qnnect.android.MyApplication.Companion.sSharedPreferences
import com.iame.qnnect.android.R
import com.iame.qnnect.android.base.BaseActivity
import com.iame.qnnect.android.databinding.ActivitySplashBinding
import com.iame.qnnect.android.src.login.LoginActivity
import com.iame.qnnect.android.src.main.MainActivity
import com.iame.qnnect.android.src.onboarding.OnboardActivity
import com.iame.qnnect.android.src.splash.model.PostRefreshRequest
import com.iame.qnnect.android.viewmodel.SplashViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.lang.Exception
import kotlin.system.exitProcess

class SplashActivity : BaseActivity<ActivitySplashBinding, SplashViewModel>() {
    override val layoutResourceId: Int
        get() = R.layout.activity_splash // get() : 커스텀 접근자, 코틀린 문법

    override val viewModel: SplashViewModel by viewModel()

    // kakao_link
    var cafeCode = "null"

    // in-app update
    var REQUEST_CODE_UPDATE = 366

    var SCHEME_MAIN = "main"

    var link = false

    var version = getAppVersion()
    var check = false

    var alarm = false

    // 앱 업데이트 매니저 초기화
//    private lateinit var appUpdateManager: AppUpdateManager

    override fun initStartView() {
        alarm = intent.getBooleanExtra("alarm", false)
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
                    baseToken.setLink(this, link)
                    Log.d("response!!", link.toString())
                    var intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                }, 1500)
            }
        })

        viewModel.versioncheckResponse.observe(this, Observer {
            if(it){
                if(!handleDynamicLinks() || alarm){
                    // model 쪽으로 넘겨야 함 데이터 이므로
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
            }
            else{
                val updateDialog: UpdateDialog = UpdateDialog { it ->
                    when (it) {
                        0 -> {
                            exitProcess(0)
                        }
                        // 업데이트 하기
                        1 -> {
                            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=com.iame.qnnect.android"))
                            startActivity(intent)
                            check = true
                        }
                    }
                }
                updateDialog.show(supportFragmentManager, updateDialog.tag)
            }
        })

        viewModel.errorversioncheckResponse.observe(this, Observer {
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        })
    }


    override fun initAfterBinding() {
    }

    override fun onResume() {
        super.onResume()
        // version check
        version = getAppVersion()
        viewModel.getVersionCheck(version!!, "android")
    }

    // dynamick link handler
    private fun handleDynamicLinks(): Boolean{
        Firebase.dynamicLinks
            .getDynamicLink(intent)
            .addOnSuccessListener(this) { pendingDynamicLinkData ->
                var deeplink: Uri? = null
                if(pendingDynamicLinkData != null) {
                    deeplink = pendingDynamicLinkData.link
                }
                else{
                    link = false
                }
                if(deeplink != null) {
                    link = true
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
                else {
                    link = false
                    Log.d("response!!", "getDynamicLink: no link found")
                }
            }
            .addOnFailureListener(this) { e -> Log.w("response!!", "getDynamicLink:onFailure", e) }
        return link
    }

    private fun getAppVersion(): String? {
        try {
            val pInfo: PackageInfo = this.packageManager.getPackageInfo(
                this.packageName, 0)
            if (pInfo != null) {
                Log.d("version_check", pInfo.versionName)
                return pInfo.versionName
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return ""
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
//
//    suspend fun Task<AppUpdateInfo>.await(): AppUpdateInfo {
//        return suspendCoroutine { continuation ->
//            addOnCompleteListener { result ->
//                if (result.isSuccessful) {
//                    continuation.resume(result.result)
//                } else {
//                    continuation.resumeWithException(result.exception!!)
//                }
//            }
//        }
//    }
//
//    // 업데이트 결과
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


