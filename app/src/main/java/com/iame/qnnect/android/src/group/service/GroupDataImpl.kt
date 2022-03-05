package com.iame.qnnect.android.src.group.service

import com.iame.qnnect.android.src.group.model.GetGroupDataModel
import com.iame.qnnect.android.src.group.model.GetGroupRequest
import com.iame.qnnect.android.src.group.model.GetGroupResponse
import com.iame.qnnect.android.src.login.model.LoginDataModel
import com.iame.qnnect.android.src.login.model.PostLoginRequest
import com.iame.qnnect.android.src.login.model.PostLoginResponse
import io.reactivex.Single

class GroupDataImpl(private val service: GroupAPI) : GetGroupDataModel {
    override fun getData(getGroupRequest: GetGroupRequest): Single<GetGroupResponse> {
        return service.getGroup(getGroupRequest.cafeId)
    }
}