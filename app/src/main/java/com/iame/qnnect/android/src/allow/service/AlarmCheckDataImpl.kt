package com.iame.qnnect.android.src.allow.service

import com.iame.qnnect.android.base.BaseToken
import com.iame.qnnect.android.model.DataModel
import com.iame.qnnect.android.model.enum.KakaoSearchSortEnum
import com.iame.qnnect.android.model.response.ImageSearchResponse
import com.iame.qnnect.android.model.service.KakaoSearchService
import com.iame.qnnect.android.src.allow.model.AlarmCheckDataModel
import com.iame.qnnect.android.src.allow.model.PatchAlarmCheckRequest
import com.iame.qnnect.android.src.allow.model.PatchAlarmCheckResponse
import com.iame.qnnect.android.src.login.model.LoginDataModel
import com.iame.qnnect.android.src.login.model.PostLoginRequest
import com.iame.qnnect.android.src.login.model.PostLoginResponse
import com.iame.qnnect.android.src.login.service.LoginAPI
import io.reactivex.Single

class AlarmCheckDataImpl(private val service: AlarmCheckAPI) : AlarmCheckDataModel {
    override fun patchAlarmCheck(header: String, enableNotification: Boolean): Single<PatchAlarmCheckResponse> {
        return service.patchAlarmCheck(header, enableNotification)
    }
}