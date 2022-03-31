package com.iame.qnnect.android.src.notarrivecafe.service

import com.iame.qnnect.android.src.login.model.LoginDataModel
import com.iame.qnnect.android.src.login.model.PostLoginRequest
import com.iame.qnnect.android.src.login.model.PostLoginResponse
import com.iame.qnnect.android.src.notarrivecafe.model.NotArriveEditDataModel
import com.iame.qnnect.android.src.notarrivecafe.model.NotArriveEditRequest
import io.reactivex.Single

class NotArriveEditDataImpl(private val service: NotArriveEditAPI) : NotArriveEditDataModel {
    override fun getData(questionId: Int, notArriveEditRequest: NotArriveEditRequest): Single<Unit> {
        return service.patchQuestion(questionId, notArriveEditRequest)
    }
}