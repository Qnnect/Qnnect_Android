package com.iame.qnnect.android.src.login

import android.content.Intent
import android.graphics.Color
import android.util.Log
import android.view.WindowManager
import android.widget.Toast
import androidx.lifecycle.Observer
import com.iame.qnnect.android.R
import com.iame.qnnect.android.base.BaseActivity
import com.iame.qnnect.android.databinding.ActivityLoginBinding
import com.iame.qnnect.android.src.allow.AllowActivity
import com.iame.qnnect.android.src.login.model.PostLoginRequest
import com.iame.qnnect.android.src.login.model.PostLoginResponse
import com.iame.qnnect.android.src.main.MainActivity
import com.iame.qnnect.android.viewmodel.LoginViewModel
import com.kakao.sdk.auth.model.OAuthToken
import com.kakao.sdk.common.model.AuthErrorCause
import com.kakao.sdk.user.UserApiClient
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_main_two.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginActivity : BaseActivity<ActivityLoginBinding, LoginViewModel>() {

    override val layoutResourceId: Int
        get() = R.layout.activity_login // get() : 커스텀 접근자, 코틀린 문법

    override val viewModel: LoginViewModel by viewModel()

    override fun initStartView() {
        var window = getWindow()
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.statusBarColor = Color.parseColor("#554338")
        window.decorView.systemUiVisibility = 0

        // 로그인 정보 확인
        UserApiClient.instance.accessTokenInfo { tokenInfo, error ->
            if (error != null) {
//                showCustomToast("토큰 정보 보기 실패")
//                Log.d("kakao_error", error.toString())
            }
            else if (tokenInfo != null) {
//                val intent = Intent(this, AllowActivity::class.java)
//                startActivity(intent)
//                finish()
            }
        }
    }

    override fun initDataBinding() {
//        val repository = Repository()
//        val viewModelFactory = LoginViewModelFactory(repository)
//        viewModel = ViewModelProvider(this,viewModelFactory).get(LoginViewModel::class.java)
//        viewModel.getPost()
//        viewModel.myResponse.observe(this, Observer {
//            Log.d("Response",it.myUserId.toString())
//            Log.d("Response",it.id.toString())
//            Log.d("Response",it.title)
//            Log.d("Response",it.body)
//        })
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
                        Toast.makeText(this, "기타 에러", Toast.LENGTH_SHORT).show()
                    }
                }
            }
            else if (token != null) {

                var accesstoken = token.accessToken
                var loginType = "kakao"
                var loginRequest = PostLoginRequest(accesstoken, loginType)
                viewModel.postLogin(loginRequest)

                viewModel.loginResponse.observe(this, Observer {
                   var response = PostLoginResponse(it.accessToken, it.isNewMember, it.refreshToken, it.userSettingDone)
                    Log.d("login_response ", response.toString())
                    baseToken.setAccessToken(this, it.accessToken, it.refreshToken)

                    if(!it.userSettingDone){
                        val intent = Intent(this, AllowActivity::class.java)
                        startActivity(intent)
                    }
                    else{
                        val intent = Intent(this, MainActivity::class.java)
                        startActivity(intent)
                    }
                })

//                Log.d("kakao_token ", token.toString())
//                val intent = Intent(this, AllowActivity::class.java)
//                startActivity(intent)

            }
        }

        kakao_login_btn.setOnClickListener {
            if(UserApiClient.instance.isKakaoTalkLoginAvailable(this)){
                UserApiClient.instance.loginWithKakaoTalk(this, callback = callback)
            }else {
                UserApiClient.instance.loginWithKakaoAccount(this, callback = callback)
            }
//            val intent = Intent(this, AllowActivity::class.java)
//            startActivity(intent)
        }
    }
}