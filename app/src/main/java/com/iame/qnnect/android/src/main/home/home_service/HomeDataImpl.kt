package com.iame.qnnect.android.src.main.home.home_service

import com.iame.qnnect.android.src.group.model.GetGroupRequest
import com.iame.qnnect.android.src.group.model.GetGroupResponse
import com.iame.qnnect.android.src.group.model.GroupDataModel
import com.iame.qnnect.android.src.main.home.home_model.GetHomeResponse
import com.iame.qnnect.android.src.main.home.home_model.HomeDataModel
import io.reactivex.Single

class HomeDataImpl(private val service: HomeAPI) : HomeDataModel {
    override fun getData(): Single<GetHomeResponse> {
        return service.patchHome()
    }
}