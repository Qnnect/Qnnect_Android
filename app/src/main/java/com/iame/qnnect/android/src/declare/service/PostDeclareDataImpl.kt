package com.iame.qnnect.android.src.declare.service

import com.iame.qnnect.android.src.declare.model.PostDeclareDataModel
import com.iame.qnnect.android.src.diary.model.DeleteQuestionDataModel
import com.iame.qnnect.android.src.diary.model.DeleteScrapDataModel
import com.iame.qnnect.android.src.diary.model.PostScrapDataModel
import com.iame.qnnect.android.src.login.model.LoginDataModel
import com.iame.qnnect.android.src.login.model.PostLoginRequest
import com.iame.qnnect.android.src.login.model.PostLoginResponse
import io.reactivex.Single

class PostDeclareDataImpl(private val service: PostDeclareAPI) : PostDeclareDataModel {
    override fun getData(reportId: Int): Single<Unit> {
        return service.postDeclare(reportId)
    }
}