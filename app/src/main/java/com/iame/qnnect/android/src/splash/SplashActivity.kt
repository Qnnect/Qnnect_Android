package com.iame.qnnect.android.src.splash

import android.content.Intent
import android.graphics.Color
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.WindowManager
import androidx.lifecycle.Observer
import com.iame.qnnect.android.MyApplication.Companion.sSharedPreferences
import com.iame.qnnect.android.R
import com.iame.qnnect.android.base.BaseActivity
import com.iame.qnnect.android.databinding.ActivitySplashBinding
import com.iame.qnnect.android.src.login.LoginActivity
import com.iame.qnnect.android.src.main.MainActivity
import com.iame.qnnect.android.src.profile.ProfileActivity
import com.iame.qnnect.android.src.splash.model.PostRefreshRequest
import com.iame.qnnect.android.viewmodel.SplashViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class SplashActivity : BaseActivity<ActivitySplashBinding, SplashViewModel>() {
    override val layoutResourceId: Int
        get() = R.layout.activity_splash // get() : 커스텀 접근자, 코틀린 문법

    override val viewModel: SplashViewModel by viewModel()

    override fun initStartView() {
        var window = getWindow()
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.statusBarColor = Color.parseColor("#554338")
        window.decorView.systemUiVisibility = 0
    }

    override fun initDataBinding() {
    }

    override fun initAfterBinding() {
        val jwtToken: String? = sSharedPreferences.getString("X-ACCESS-TOKEN", null)
        val refreshToken: String? = sSharedPreferences.getString("refresh-token", null)

        if(jwtToken != null){
            var refreshRequest = PostRefreshRequest(jwtToken!!, refreshToken!!)

            viewModel.postRefresh(refreshRequest)

            viewModel.refreshResponse.observe(this, Observer {
                if(it.accessToken == "" || it.refreshToken == ""){
                    Handler(Looper.getMainLooper()).postDelayed({
                        startActivity(Intent(this, LoginActivity::class.java))
                        finish()
                    }, 1500)
                }
                else{
                    Log.d("login_response", it.toString())
                    baseToken.setAccessToken(this, it.accessToken, it.refreshToken)
                    Handler(Looper.getMainLooper()).postDelayed({
                        startActivity(Intent(this, MainActivity::class.java))
                        finish()
                    }, 1500)
                }
            })
        }
        else{
            Handler(Looper.getMainLooper()).postDelayed({
                startActivity(Intent(this, LoginActivity::class.java))
                finish()
            }, 1500)
        }

    }
}
