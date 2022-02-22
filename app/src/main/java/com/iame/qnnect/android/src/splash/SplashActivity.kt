package com.iame.qnnect.android.src.splash

import android.content.Intent
import android.graphics.Color
import android.os.Handler
import android.os.Looper
import android.view.WindowManager
import com.iame.qnnect.android.R
import com.iame.qnnect.android.base.BaseActivity
import com.iame.qnnect.android.databinding.ActivitySplashBinding
import com.iame.qnnect.android.src.invite.InviteActivity
import com.iame.qnnect.android.src.login.LoginActivity
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
        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }, 1500)
    }
}
