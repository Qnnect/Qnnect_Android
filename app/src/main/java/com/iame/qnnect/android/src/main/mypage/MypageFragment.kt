package com.iame.qnnect.android.src.main.mypage

import android.content.Intent
import android.net.Uri
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.iame.qnnect.android.R
import com.iame.qnnect.android.base.BaseFragment
import com.iame.qnnect.android.databinding.FragmentMyPageBinding
import com.iame.qnnect.android.src.alarm.AlarmActivity
import com.iame.qnnect.android.src.login.LoginActivity
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

        alarm_btn.setOnClickListener {
            var intent = Intent(context, AlarmActivity::class.java)
            startActivity(intent)
        }

        user_profile_img.setOnClickListener {
            var intent = Intent(context, EditprofileActivity::class.java)
            startActivity(intent)
        }

        setting_logout.setOnClickListener {
            val logoutDialog: LogoutDialog = LogoutDialog {
                when (it) {
                    // 로그아웃
                    0 -> {
                        baseToken.setAccessToken(requireContext(), "", "")
                        var intent = Intent(context, LoginActivity::class.java)
                        intent.flags =
                            Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP //액티비티 스택제거
                        startActivity(intent)
                    }
                }
            }
            logoutDialog.show(requireActivity().supportFragmentManager, logoutDialog.tag)
        }

        setting_instagram.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.instagram.com/qnnect.official/"))
            startActivity(intent)
        }

    }

    override fun onResume() {
        super.onResume()
        viewModel.getUser()

        viewModel.userResponse.observe(this, Observer {
            var image = it.profileImage

            // Profile Url
            Glide.with(this)
                .load(image)
                .transform(CenterCrop(), RoundedCorners(200))
                .into(user_profile_img)
            // User Name
            user_diary_name.text = it.nickName+"님의 다이어리"
            // User Point
            point_txt.text = it.point.toString()+"P"
        })
    }

}