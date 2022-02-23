package com.iame.qnnect.android.base

import com.iame.qnnect.android.src.login.model.PostLoginRequest
import com.iame.qnnect.android.src.login.model.PostLoginResponse

class Repository {
    fun postLogin(postLoginRequest: PostLoginRequest) : PostLoginResponse {
        return RetrofitInstance.loginAPI.postLogin(postLoginRequest)
    }
}