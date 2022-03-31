package com.iame.qnnect.android.src.notarrivecafe.model

import com.iame.qnnect.android.src.login.model.PostLoginRequest
import com.iame.qnnect.android.src.login.model.PostLoginResponse
import io.reactivex.Single

interface NotArriveEditDataModel {
    fun getData(questionId: Int, notArriveEditRequest: NotArriveEditRequest): Single<Unit>
}

