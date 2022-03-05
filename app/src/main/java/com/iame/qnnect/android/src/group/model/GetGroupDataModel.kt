package com.iame.qnnect.android.src.group.model

import io.reactivex.Single

interface GetGroupDataModel {
    fun getData(getGroupRequest: GetGroupRequest): Single<GetGroupResponse>
}

