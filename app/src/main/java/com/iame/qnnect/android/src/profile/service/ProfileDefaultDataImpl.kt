package com.iame.qnnect.android.src.profile.service

import com.iame.qnnect.android.src.login.model.LoginDataModel
import com.iame.qnnect.android.src.login.model.PostLoginRequest
import com.iame.qnnect.android.src.login.model.PostLoginResponse
import com.iame.qnnect.android.src.profile.model.PatchProfileResponse
import com.iame.qnnect.android.src.profile.model.ProfileDataModel
import com.iame.qnnect.android.src.profile.model.ProfileDefaultDataModel
import io.reactivex.Single
import okhttp3.MultipartBody

class ProfileDefaultDataImpl(private val service: ProfileDefaultAPI) : ProfileDefaultDataModel {
    override fun getData(): Single<Unit> {
        return service.defaultProfile()
    }
}