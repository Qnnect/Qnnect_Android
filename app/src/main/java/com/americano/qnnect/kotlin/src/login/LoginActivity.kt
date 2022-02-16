package com.americano.qnnect.kotlin.src.login

import android.content.Intent
import com.americano.qnnect.kotlin.R
import com.americano.qnnect.kotlin.base.BaseActivity
import com.americano.qnnect.kotlin.databinding.ActivityLoginBinding
import com.americano.qnnect.kotlin.src.allow.AllowActivity
import com.americano.qnnect.kotlin.viewmodel.LoginViewModel
import kotlinx.android.synthetic.main.activity_login.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginActivity : BaseActivity<ActivityLoginBinding, LoginViewModel>() {

    override val layoutResourceId: Int
        get() = R.layout.activity_login // get() : 커스텀 접근자, 코틀린 문법

    override val viewModel: LoginViewModel by viewModel()

    override fun initStartView() {
    }

    override fun initDataBinding() {
    }

    override fun initAfterBinding() {
        kakao_login_btn.setOnClickListener {
            var intent = Intent(this, AllowActivity::class.java)
            startActivity(intent)
        }
    }


}