package com.iame.qnnect.android.src.main.bookmark.service

import com.iame.qnnect.android.src.group.model.GetGroupRequest
import com.iame.qnnect.android.src.group.model.GetGroupResponse
import com.iame.qnnect.android.src.group.model.GroupDataModel
import com.iame.qnnect.android.src.main.bookmark.model.Cafe
import com.iame.qnnect.android.src.main.bookmark.model.CafeListDataModel
import com.iame.qnnect.android.src.main.bookmark.model.GetCafeListResponse
import com.iame.qnnect.android.src.main.home.home_model.GetHomeResponse
import com.iame.qnnect.android.src.main.home.home_model.HomeDataModel
import io.reactivex.Single

class CafeListDataImpl(private val service: CafeListAPI) : CafeListDataModel {
    override fun getData(): Single<List<Cafe>> {
        return service.getCafe()
    }
}