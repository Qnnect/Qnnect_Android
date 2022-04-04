package com.iame.qnnect.android.src.invite

import android.util.Log
import com.iame.qnnect.android.R
import com.iame.qnnect.android.base.BaseActivity
import com.iame.qnnect.android.databinding.ActivityInviteBinding
import com.iame.qnnect.android.viewmodel.InviteViewModel
import com.kakao.sdk.link.LinkClient
import kotlinx.android.synthetic.main.activity_invite.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.widget.Toast
import com.google.firebase.dynamiclinks.FirebaseDynamicLinks
import com.google.firebase.dynamiclinks.internal.FirebaseDynamicLinkRegistrar
import com.google.firebase.dynamiclinks.ktx.androidParameters
import com.google.firebase.dynamiclinks.ktx.dynamicLinks
import com.google.firebase.dynamiclinks.ktx.shortLinkAsync
import com.google.firebase.ktx.Firebase
import com.iame.qnnect.android.BuildConfig
import com.iame.qnnect.android.src.declare.DeclareBottomSheet
import com.kakao.sdk.common.util.KakaoCustomTabsClient
import com.kakao.sdk.link.WebSharerClient
import kotlin.reflect.KParameter


class InviteActivity : BaseActivity<ActivityInviteBinding, InviteViewModel>() {

    override val layoutResourceId: Int
        get() = R.layout.activity_invite // get() : 커스텀 접근자, 코틀린 문법

    override val viewModel: InviteViewModel by viewModel()
    var code = ""
    var title = ""

    override fun initStartView() {
    }

    override fun initDataBinding() {
        code = intent.getStringExtra("code")!!
        title = intent.getStringExtra("title")!!

        text4.text = title+" 카페에\n초대합니다!"
    }

    override fun initAfterBinding() {
        back_btn.setOnClickListener {
            finish()
        }

        kakao_btn.setOnClickListener {
            sendKakaoLink()
        }

        link_btn.setOnClickListener {
            val inviteBottomSheet: InviteBottomSheet = InviteBottomSheet {
                when (it) {
                    // 초대 링크 복사
                    0 -> {
                        val sendIntent: Intent = Intent().apply {
                            action = Intent.ACTION_SEND
                            putExtra(Intent.EXTRA_TEXT, "https://iame.page.link/Fc4u")
                            type = "text/plain"
                        }

                        val shareIntent = Intent.createChooser(sendIntent, title+"의 초대!")
                        startActivity(shareIntent)
                    }
                    // 초대 코드 복사
                    1 -> {
                        Toast.makeText(this, "카페 코드 복사 완료", Toast.LENGTH_SHORT).show()
                        val clipboard: ClipboardManager =
                            getSystemService(CLIPBOARD_SERVICE) as ClipboardManager
                        val clip = ClipData.newPlainText("label", code)
                        clipboard.setPrimaryClip(clip)
                    }
                }
            }
            inviteBottomSheet.show(supportFragmentManager, inviteBottomSheet.tag)
        }
    }

    private fun sendKakaoLink() {

        val templateId: Long = 73322

        val templateArgs: MutableMap<String, String> = HashMap()
        templateArgs["code"] = code
        templateArgs["group_name"] = title

        // 피드 메시지 보내기
        LinkClient.instance.customTemplate(this, templateId, templateArgs) { linkResult, error ->
            if (error != null) {
                Log.e("invite_kakao", "카카오링크 보내기 실패", error)
                Toast.makeText(this, "카카오톡 설치후 이용해주세요.", Toast.LENGTH_SHORT).show()
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