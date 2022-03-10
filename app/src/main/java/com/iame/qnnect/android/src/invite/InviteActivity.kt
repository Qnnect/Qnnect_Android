package com.iame.qnnect.android.src.invite

import android.util.Log
import com.iame.qnnect.android.R
import com.iame.qnnect.android.base.BaseActivity
import com.iame.qnnect.android.databinding.ActivityInviteBinding
import com.iame.qnnect.android.viewmodel.InviteViewModel
import com.kakao.sdk.link.LinkClient
import com.kakao.sdk.template.model.*
import kotlinx.android.synthetic.main.activity_invite.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class InviteActivity : BaseActivity<ActivityInviteBinding, InviteViewModel>() {

    override val layoutResourceId: Int
        get() = R.layout.activity_invite // get() : 커스텀 접근자, 코틀린 문법

    override val viewModel: InviteViewModel by viewModel()
    var code = ""

    override fun initStartView() {
    }

    override fun initDataBinding() {
//        code = intent.getStringExtra("code")!!
    }

    override fun initAfterBinding() {
        back_btn.setOnClickListener {
            finish()
        }

        kakao_btn.setOnClickListener {
            sendKakaoLink()
        }
    }

    private fun sendKakaoLink() {
        // 메시지 템플릿 만들기 (피드형)
        val defaultFeed = FeedTemplate(
            content = Content(
                title = "큐넥트",
                description = "~~방에서 초대를 했어요!",
                imageUrl = "R.mipmap.img_logo_name_foreground",
                link = Link(
                    mobileWebUrl = "https://play.google.com/store/apps/details?id=com.mtjin.nomoneytrip"
                )
            ), social = Social(
                likeCount = 4
            ),
            buttons = listOf(
                Button(
                    "웹으로 보기",
                    Link(
                        webUrl = "https://developers.kakao.com",
                        mobileWebUrl = "https://developers.kakao.com"
                    )
                ),
                Button(
                    "앱으로 보기",
                    Link(
                        androidExecutionParams = mapOf("key1" to "value1", "key2" to "value2"),
                        iosExecutionParams = mapOf("key1" to "value1", "key2" to "value2")
                    )
                )
            )
        )

        // 피드 메시지 보내기
        LinkClient.instance.defaultTemplate(this, defaultFeed) { linkResult, error ->
            if (error != null) {
                Log.e("invite_kakao", "카카오링크 보내기 실패", error)
            }
            else if (linkResult != null) {
                Log.d("invite_kakao", "카카오링크 보내기 성공 ${linkResult.intent}")
                startActivity(linkResult.intent)

                // 카카오링크 보내기에 성공했지만 아래 경고 메시지가 존재할 경우 일부 컨텐츠가 정상 동작하지 않을 수 있습니다.
                Log.w("invite_kakao", "Warning Msg: ${linkResult.warningMsg}")
                Log.w("invite_kakao", "Argument Msg: ${linkResult.argumentMsg}")
            }
        }
    }
}