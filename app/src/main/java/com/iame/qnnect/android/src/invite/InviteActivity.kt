package com.iame.qnnect.android.src.invite

import com.iame.qnnect.android.R
import com.iame.qnnect.android.base.BaseActivity
import com.iame.qnnect.android.databinding.ActivityInviteBinding
import com.iame.qnnect.android.viewmodel.InviteViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class InviteActivity : BaseActivity<ActivityInviteBinding, InviteViewModel>() {

    override val layoutResourceId: Int
        get() = R.layout.activity_invite // get() : 커스텀 접근자, 코틀린 문법

    override val viewModel: InviteViewModel by viewModel()

    override fun initStartView() {
    }

    override fun initDataBinding() {
    }

    override fun initAfterBinding() {
    }
}