package com.iame.qnnect.android.src.diary.service

import com.iame.qnnect.android.src.diary.model.PostScrapDataModel
import com.iame.qnnect.android.src.login.model.LoginDataModel
import com.iame.qnnect.android.src.login.model.PostLoginRequest
import com.iame.qnnect.android.src.login.model.PostLoginResponse
import io.reactivex.Single

class PostScrapDataImpl(private val service: PostScrapAPI) : PostScrapDataModel {
    override fun getData(cafeQuestionId: Int): Single<Unit> {
        return service.postScrap(cafeQuestionId)
    }
}