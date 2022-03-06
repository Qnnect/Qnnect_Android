package com.iame.qnnect.android.src.group.model

import com.iame.qnnect.android.src.login.model.PostLoginRequest
import com.iame.qnnect.android.src.login.model.PostLoginResponse
import io.reactivex.Single

interface GroupDataModel {
    fun getData(cafeId: Int): Single<GetGroupResponse>
}

