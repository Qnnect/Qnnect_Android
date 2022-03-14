package com.iame.qnnect.android.src.profile.service

import com.iame.qnnect.android.src.login.model.LoginDataModel
import com.iame.qnnect.android.src.login.model.PostLoginRequest
import com.iame.qnnect.android.src.login.model.PostLoginResponse
import com.iame.qnnect.android.src.profile.model.PatchProfileResponse
import com.iame.qnnect.android.src.profile.model.ProfileDataModel
import com.iame.qnnect.android.src.profile.model.ProfileDefaultDataModel
import io.reactivex.Single
import okhttp3.MultipartBody

class ProfileDataImpl(private val service: ProfileAPI) : ProfileDataModel {
    override fun getData(
        profilePicture: MultipartBody.Part?,
        nickName: MultipartBody.Part?,
    ): Single<PatchProfileResponse> {
        return service.patchProfile(profilePicture, nickName)
    }
}