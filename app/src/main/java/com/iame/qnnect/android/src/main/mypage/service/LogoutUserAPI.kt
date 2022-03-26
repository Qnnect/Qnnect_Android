package com.iame.qnnect.android.src.main.mypage.service

import com.iame.qnnect.android.src.login.model.PostLoginRequest
import com.iame.qnnect.android.src.login.model.PostLoginResponse
import io.reactivex.Single
import retrofit2.http.*

interface LogoutUserAPI {
    @PATCH("/api/v1/auth/logout")
    fun logoutUser() : Single<Unit>
}