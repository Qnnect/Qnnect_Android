package com.iame.qnnect.android.viewmodel

import androidx.lifecycle.viewModelScope
import com.iame.qnnect.android.base.BaseViewModel
import com.iame.qnnect.android.base.Repository
import com.iame.qnnect.android.src.login.model.PostLoginRequest
import com.iame.qnnect.android.src.login.model.PostLoginResponse
import kotlinx.coroutines.launch

class LoginViewModel(private val repository : Repository) : BaseViewModel() {

    private val TAG = "LoginViewModel"

    lateinit var postLoginResponse: PostLoginResponse

    fun postLogin(postLoginRequest: PostLoginRequest) {
        viewModelScope.launch {
            val response = repository.postLogin(postLoginRequest)
            postLoginResponse.accessToken = response.accessToken
            postLoginResponse.loginType = response.loginType
            postLoginResponse.refreshToken = response.refreshToken
        }
    }
}