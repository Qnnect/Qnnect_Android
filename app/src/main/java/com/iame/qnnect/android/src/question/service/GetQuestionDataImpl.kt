package com.iame.qnnect.android.src.question.service

import com.iame.qnnect.android.src.login.model.LoginDataModel
import com.iame.qnnect.android.src.login.model.PostLoginRequest
import com.iame.qnnect.android.src.login.model.PostLoginResponse
import com.iame.qnnect.android.src.profile.model.PatchProfileResponse
import com.iame.qnnect.android.src.profile.model.ProfileDataModel
import com.iame.qnnect.android.src.profile.service.ProfileAPI
import com.iame.qnnect.android.src.question.model.GetQuestionDataModel
import com.iame.qnnect.android.src.question.model.GetQuestionResponse
import com.iame.qnnect.android.src.question.model.PostQuestionDataModel
import com.iame.qnnect.android.src.question.model.PostQuestionRequest
import io.reactivex.Single
import okhttp3.MultipartBody

class GetQuestionDataImpl(private val service: GetQuestionAPI) : GetQuestionDataModel {
    override fun getData(cafeId: Int): Single<GetQuestionResponse> {
        return service.getQuestion(cafeId)
    }
}