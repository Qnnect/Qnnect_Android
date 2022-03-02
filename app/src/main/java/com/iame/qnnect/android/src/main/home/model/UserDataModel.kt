package com.iame.qnnect.android.src.main.home.model

import com.iame.qnnect.android.src.login.model.PostLoginResponse
import io.reactivex.Single

interface UserDataModel {
    fun getData(): Single<GetUserResponse>
}

