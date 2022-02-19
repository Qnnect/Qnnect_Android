package com.iame.qnnect.android.src.main.mypage

import android.content.Intent
import com.iame.qnnect.android.R
import com.iame.qnnect.android.base.BaseFragment
import com.iame.qnnect.android.databinding.FragmentMyPageBinding
import com.iame.qnnect.android.viewmodel.MypageViewModel
import kotlinx.android.synthetic.main.fragment_my_page.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class MypageFragment : BaseFragment<FragmentMyPageBinding, MypageViewModel>(R.layout.fragment_my_page) {

    override val layoutResourceId: Int
        get() = R.layout.fragment_my_page // get() : 커스텀 접근자, 코틀린 문법

    override val viewModel: MypageViewModel by viewModel()

    override fun initStartView() {
    }

    override fun initDataBinding() {
    }

    override fun initAfterBinding() {
        user_profile_img.setOnClickListener {
            var intent = Intent(context, EditprofileActivity::class.java)
            startActivity(intent)
        }
    }

}