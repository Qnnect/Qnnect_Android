package com.americano.qnnect.kotlin.src.login

import android.content.Intent
import android.os.Bundle
import com.americano.qnnect.kotlin.config.BaseActivity
import com.americano.qnnect.kotlin.databinding.ActivityLoginBinding
import com.americano.qnnect.kotlin.src.allow.AllowActivity

class LoginActivity : BaseActivity<ActivityLoginBinding>(ActivityLoginBinding::inflate) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.kakaoLoginBtn.setOnClickListener {
            var intent = Intent(this, AllowActivity::class.java)
            startActivity(intent)
        }
    }
}