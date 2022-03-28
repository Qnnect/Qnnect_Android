package com.iame.qnnect.android.src.stamp.service

import com.iame.qnnect.android.src.splash.model.PostRefreshRequest
import com.iame.qnnect.android.src.splash.model.PostRefreshResponse
import com.iame.qnnect.android.src.splash.model.RefreshDataModel
import com.iame.qnnect.android.src.stamp.model.Stamp
import com.iame.qnnect.android.src.stamp.model.StampDataModel
import io.reactivex.Single

class StampDataImpl(private val service: StampAPI) : StampDataModel {
    override fun getStamp(): Single<List<Stamp>> {
        return service.getStamp()
    }
}