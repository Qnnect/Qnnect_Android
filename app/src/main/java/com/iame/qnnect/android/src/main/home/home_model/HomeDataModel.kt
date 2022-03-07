package com.iame.qnnect.android.src.main.home.home_model

import com.iame.qnnect.android.src.group.model.GetGroupResponse
import com.iame.qnnect.android.src.login.model.PostLoginRequest
import com.iame.qnnect.android.src.login.model.PostLoginResponse
import io.reactivex.Single

interface HomeDataModel {
    fun getData(): Single<GetHomeResponse>
}

