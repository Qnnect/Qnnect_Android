package com.americano.qnnect.kotlin.src.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.americano.qnnect.kotlin.R
import com.americano.qnnect.kotlin.base.BaseActivity
import com.americano.qnnect.kotlin.databinding.ActivitySplashBinding
import com.americano.qnnect.kotlin.src.login.LoginActivity
import com.americano.qnnect.kotlin.src.profile.ProfileActivity
import com.americano.qnnect.kotlin.viewmodel.AllowViewModel
import com.americano.qnnect.kotlin.viewmodel.SplashViewModel
import kotlinx.android.synthetic.main.activity_allow.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class SplashActivity : BaseActivity<ActivitySplashBinding, SplashViewModel>() {
    override val layoutResourceId: Int
        get() = R.layout.activity_splash // get() : 커스텀 접근자, 코틀린 문법

    override val viewModel: SplashViewModel by viewModel()

    override fun initStartView() {
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
