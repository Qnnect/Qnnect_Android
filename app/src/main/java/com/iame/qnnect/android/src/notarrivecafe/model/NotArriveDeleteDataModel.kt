package com.iame.qnnect.android.src.notarrivecafe.model

import com.iame.qnnect.android.src.login.model.PostLoginRequest
import com.iame.qnnect.android.src.login.model.PostLoginResponse
import io.reactivex.Single

interface NotArriveDeleteDataModel {
    fun getData(questionId: Int): Single<Unit>
}

