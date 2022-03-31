package com.iame.qnnect.android.src.notarrivecafe.service

import com.iame.qnnect.android.src.login.model.LoginDataModel
import com.iame.qnnect.android.src.login.model.PostLoginRequest
import com.iame.qnnect.android.src.login.model.PostLoginResponse
import com.iame.qnnect.android.src.notarrivecafe.model.NotArriveDeleteDataModel
import com.iame.qnnect.android.src.notarrivecafe.model.NotArriveEditDataModel
import com.iame.qnnect.android.src.notarrivecafe.model.NotArriveEditRequest
import io.reactivex.Single

class NotArriveDeleteDataImpl(private val service: NotArriveDeleteAPI) : NotArriveDeleteDataModel {
    override fun getData(questionId: Int): Single<Unit> {
        return service.deleteQuestion(questionId)
    }
}