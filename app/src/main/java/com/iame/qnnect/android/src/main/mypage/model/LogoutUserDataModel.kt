package com.iame.qnnect.android.src.main.mypage.model

import io.reactivex.Single

interface LogoutUserDataModel {
    fun getData(): Single<Unit>
}

