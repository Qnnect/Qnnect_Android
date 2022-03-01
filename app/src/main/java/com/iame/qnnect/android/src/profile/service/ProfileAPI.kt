package com.iame.qnnect.android.src.profile.service

import com.iame.qnnect.android.src.profile.model.PatchProfileResponse
import io.reactivex.Single
import okhttp3.MultipartBody
import retrofit2.http.Multipart
import retrofit2.http.PATCH
import retrofit2.http.Part

interface ProfileAPI {
    @Multipart
    @PATCH("/api/v1/user/profile")
    fun postLogin(@Part nickname: MultipartBody.Part, @Part image: MultipartBody.Part?) : Single<PatchProfileResponse>
}
