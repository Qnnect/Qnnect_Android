package com.iame.qnnect.android.src.question.service

import com.iame.qnnect.android.src.login.model.LoginDataModel
import com.iame.qnnect.android.src.login.model.PostLoginRequest
import com.iame.qnnect.android.src.login.model.PostLoginResponse
import com.iame.qnnect.android.src.profile.model.PatchProfileResponse
import com.iame.qnnect.android.src.profile.model.ProfileDataModel
import com.iame.qnnect.android.src.profile.service.ProfileAPI
import com.iame.qnnect.android.src.question.model.PostQuestionDataModel
import com.iame.qnnect.android.src.question.model.PostQuestionRequest
import io.reactivex.Single
import okhttp3.MultipartBody

class PostQuestionDataImpl(private val service: PostQuestionAPI) : PostQuestionDataModel {
    override fun getData(cafeId: Int, contents: PostQuestionRequest): Single<Int> {
        return service.postQuestion(cafeId, contents)
    }
}