package com.americano.qnnect.kotlin.src.profile

import android.content.Intent
import android.os.Bundle
import com.americano.qnnect.kotlin.R
import com.americano.qnnect.kotlin.base.BaseActivity
import com.americano.qnnect.kotlin.databinding.ActivityProfileBinding
import com.americano.qnnect.kotlin.src.allow.AllowActivity
import com.americano.qnnect.kotlin.viewmodel.LoginViewModel
import com.americano.qnnect.kotlin.viewmodel.ProfileViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class ProfileActivity : BaseActivity<ActivityProfileBinding, ProfileViewModel>() {

    override val layoutResourceId: Int
        get() = R.layout.activity_profile // get() : 커스텀 접근자, 코틀린 문법

    override val viewModel: ProfileViewModel by viewModel()

    override fun initStartView() {
    }

    override fun initDataBinding() {
    }

    override fun initAfterBinding() {
    }


}