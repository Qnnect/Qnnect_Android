package com.iame.qnnect.android.src.diary.service

import com.iame.qnnect.android.src.diary.model.GetQuestionDataModel
import com.iame.qnnect.android.src.diary.model.GetQuestionResponse
import com.iame.qnnect.android.src.diary.model.PostScrapDataModel
import com.iame.qnnect.android.src.login.model.LoginDataModel
import com.iame.qnnect.android.src.login.model.PostLoginRequest
import com.iame.qnnect.android.src.login.model.PostLoginResponse
import io.reactivex.Single

class GetQuestionDataImpl(private val service: GetQuestionAPI) : GetQuestionDataModel {
    override fun getData(cafeQuestionId: Int): Single<GetQuestionResponse> {
        return service.getQuestion(cafeQuestionId)
    }
}