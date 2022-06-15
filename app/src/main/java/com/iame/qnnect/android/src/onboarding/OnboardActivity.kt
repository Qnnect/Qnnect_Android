package com.iame.qnnect.android.src.onboarding

import com.iame.qnnect.android.base.BaseActivity
import com.iame.qnnect.android.databinding.ActivityOnboardBinding
import com.iame.qnnect.android.viewmodel.OnboardViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import kotlinx.android.synthetic.main.activity_onboard.*
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_home.dots_indicator


class OnboardActivity : BaseActivity<ActivityOnboardBinding, OnboardViewModel>() {

    override val layoutResourceId: Int
        get() = com.iame.qnnect.android.R.layout.activity_onboard // get() : 커스텀 접근자, 코틀린 문법

    override val viewModel: OnboardViewModel by viewModel()

    override fun initStartView() {
        // question viewpager
        binding.viewpager2.adapter = OnboardAdapter(this, 4)
        //인디케이터 타입1
        val dotsIndicator = binding.dotsIndicator
        dotsIndicator.setViewPager2(viewpager2)
    }

    override fun initDataBinding() {
    }

    override fun initAfterBinding() {

    }

}