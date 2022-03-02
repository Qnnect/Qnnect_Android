package com.iame.qnnect.android.src.main.home.service

import com.iame.qnnect.android.src.login.model.LoginDataModel
import com.iame.qnnect.android.src.login.model.PostLoginRequest
import com.iame.qnnect.android.src.login.model.PostLoginResponse
import com.iame.qnnect.android.src.main.home.model.GetUserResponse
import com.iame.qnnect.android.src.main.home.model.UserDataModel
import io.reactivex.Single

class UserDataImpl(private val service: UserAPI) : UserDataModel {
    override fun getData(): Single<GetUserResponse> {
        return service.getUser()
    }
}