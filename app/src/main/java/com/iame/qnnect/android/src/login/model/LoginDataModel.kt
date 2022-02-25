package com.iame.qnnect.android.src.login.model

import io.reactivex.Single

interface LoginDataModel {
    fun getData(postLoginRequest: PostLoginRequest): Single<PostLoginResponse>
}

