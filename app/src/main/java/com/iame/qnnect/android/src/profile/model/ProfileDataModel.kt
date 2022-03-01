package com.iame.qnnect.android.src.profile.model

import com.iame.qnnect.android.src.login.model.PostLoginRequest
import com.iame.qnnect.android.src.login.model.PostLoginResponse
import io.reactivex.Single
import okhttp3.MultipartBody
import retrofit2.http.Part

interface ProfileDataModel {
    fun getData(@Part nickname: MultipartBody.Part, @Part image: MultipartBody.Part?): Single<PatchProfileResponse>
}

