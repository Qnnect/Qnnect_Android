package com.americano.qnnect.kotlin.src.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.americano.qnnect.kotlin.config.BaseActivity
import com.americano.qnnect.kotlin.databinding.ActivitySplashBinding
import com.americano.qnnect.kotlin.src.allow.AllowActivity
import com.americano.qnnect.kotlin.src.login.LoginActivity

class SplashActivity : BaseActivity<ActivitySplashBinding>(ActivitySplashBinding::inflate) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }, 1500)
    }
}