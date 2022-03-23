package com.iame.qnnect.android.src.main.mypage.model

import io.reactivex.Single

interface DeleteUserDataModel {
    fun getData(): Single<Unit>
}

