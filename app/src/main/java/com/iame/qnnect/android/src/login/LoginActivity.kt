package com.iame.qnnect.android.src.login

import android.content.Intent
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.Observer
import com.iame.qnnect.android.R
import com.iame.qnnect.android.base.BaseActivity
import com.iame.qnnect.android.databinding.ActivityLoginBinding
import com.iame.qnnect.android.src.allow.AllowActivity
import com.iame.qnnect.android.src.login.model.PostLoginRequest
import com.iame.qnnect.android.src.main.MainActivity
import com.iame.qnnect.android.src.profile.ProfileActivity
import com.iame.qnnect.android.viewmodel.LoginViewModel
import com.kakao.sdk.auth.model.OAuthToken
import com.kakao.sdk.common.model.AuthErrorCause
import com.kakao.sdk.talk.TalkApiClient
import com.kakao.sdk.talk.model.TalkProfile
import com.kakao.sdk.user.UserApiClient
import kotlinx.android.synthetic.main.activity_login.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginActivity : BaseActivity<ActivityLoginBinding, LoginViewModel>() {

    override val layoutResourceId: Int
        get() = R.layout.activity_login // get() : 커스텀 접근자, 코틀린 문법

    override val viewModel: LoginViewModel by viewModel()

    override fun initStartView() {
    }

    override fun initDataBinding() {
        viewModel.loginResponse.observe(this, Observer {
//            Log.d("login_response ", it.toString())
            baseToken.setAccessToken(this, it.accessToken, it.refreshToken)
            dismissLoadingDialog()

            if(!it.userSettingDone){
                val intent = Intent(this, AllowActivity::class.java)
                startActivity(intent)
                finish()
            }
            else{
                val fcmToken = baseToken.getFCM(this)
                if(fcmToken != null){
                    viewModel.postFcmToken(fcmToken)
//                    val intent = Intent(this, MainActivity::class.java)
//                    startActivity(intent)
                }
                else{
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                }
            }

            viewModel.fcmtokenResponse.observe(this, Observer {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            })

            viewModel.errorResponse.observe(this, Observer {
                Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
            })
        })

        // 로그인 정보 확인
        UserApiClient.instance.accessTokenInfo { tokenInfo, error ->
            if (error != null) {
            }
            else if (tokenInfo != null) {
            }
        }
    }

    override fun initAfterBinding() {
        val callback: (OAuthToken?, Throwable?) -> Unit = { token, error ->
            if (error != null) {
                Log.e("response!!", error.toString())
                when {
                    error.toString() == AuthErrorCause.AccessDenied.toString() -> {
                        Toast.makeText(this, "접근이 거부 됨(동의 취소)", Toast.LENGTH_SHORT).show()
                    }
                    error.toString() == AuthErrorCause.InvalidClient.toString() -> {
                        Toast.makeText(this, "유효하지 않은 앱", Toast.LENGTH_SHORT).show()
                    }
                    error.toString() == AuthErrorCause.InvalidGrant.toString() -> {
                        Toast.makeText(this, "인증 수단이 유효하지 않아 인증할 수 없는 상태", Toast.LENGTH_SHORT).show()
                    }
                    error.toString() == AuthErrorCause.InvalidRequest.toString() -> {
                        Toast.makeText(this, "요청 파라미터 오류", Toast.LENGTH_SHORT).show()
                    }
                    error.toString() == AuthErrorCause.InvalidScope.toString() -> {
                        Toast.makeText(this, "유효하지 않은 scope ID", Toast.LENGTH_SHORT).show()
                    }
                    error.toString() == AuthErrorCause.Misconfigured.toString() -> {
                        Toast.makeText(this, "설정이 올바르지 않음(android key hash)", Toast.LENGTH_SHORT).show()
                    }
                    error.toString() == AuthErrorCause.ServerError.toString() -> {
                        Toast.makeText(this, "서버 내부 에러", Toast.LENGTH_SHORT).show()
                    }
                    error.toString() == AuthErrorCause.Unauthorized.toString() -> {
                        Toast.makeText(this, "앱이 요청 권한이 없음", Toast.LENGTH_SHORT).show()
                    }
                    else -> { // Unknown
                        Toast.makeText(this, "카카오톡의 미로그인", Toast.LENGTH_SHORT).show()
                    }
                }
            }

            else if (token != null) {
                var accesstoken = token.accessToken
                var loginType = "kakao"
                var loginRequest = PostLoginRequest(accesstoken, loginType)
                var result: OAuthToken = token
                viewModel.postLogin(loginRequest)
                showLoadingDialog(this)
            }
        }



        viewDataBinding.kakaoLoginBtn.setOnClickListener {
            if(UserApiClient.instance.isKakaoTalkLoginAvailable(this)){
                UserApiClient.instance.loginWithKakaoTalk(this, callback = callback)
            }else {
                UserApiClient.instance.loginWithKakaoAccount(this, callback = callback)
            }
        }
    }
}