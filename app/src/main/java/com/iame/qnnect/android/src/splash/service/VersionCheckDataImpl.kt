package com.iame.qnnect.android.src.splash.service

import com.iame.qnnect.android.src.splash.model.PostRefreshRequest
import com.iame.qnnect.android.src.splash.model.PostRefreshResponse
import com.iame.qnnect.android.src.splash.model.RefreshDataModel
import com.iame.qnnect.android.src.splash.model.VersionCheckDataModel
import io.reactivex.Single

class VersionCheckDataImpl(private val service: VersionCheckAPI) : VersionCheckDataModel {
    override fun getVersionCheck(currentVersion: String, osType: String): Single<Boolean> {
        return service.getVersionCheck(currentVersion, osType)
    }
}