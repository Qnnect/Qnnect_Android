package com.iame.qnnect.android.src.group.service

import com.iame.qnnect.android.src.group.model.GetGroupRequest
import com.iame.qnnect.android.src.group.model.GetGroupResponse
import com.iame.qnnect.android.src.group.model.GroupDataModel
import io.reactivex.Single

class GroupDataImpl(private val service: GroupAPI) : GroupDataModel {
    override fun getData(cafeId: Int): Single<GetGroupResponse> {
        return service.getGroup(cafeId)
    }
}