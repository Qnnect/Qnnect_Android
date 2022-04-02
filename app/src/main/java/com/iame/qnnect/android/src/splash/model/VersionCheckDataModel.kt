package com.iame.qnnect.android.src.splash.model

import com.iame.qnnect.android.model.enum.KakaoSearchSortEnum
import com.iame.qnnect.android.model.response.ImageSearchResponse
import com.iame.qnnect.android.src.login.model.PostLoginRequest
import com.iame.qnnect.android.src.login.model.PostLoginResponse
import io.reactivex.Single

interface VersionCheckDataModel {
    fun getVersionCheck(currentVersion: String, osType: String): Single<Boolean>
}

