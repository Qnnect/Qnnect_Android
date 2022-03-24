package com.iame.qnnect.android.src.question.service

import com.iame.qnnect.android.src.login.model.LoginDataModel
import com.iame.qnnect.android.src.login.model.PostLoginRequest
import com.iame.qnnect.android.src.login.model.PostLoginResponse
import com.iame.qnnect.android.src.main.bookmark.model.Bookmark
import com.iame.qnnect.android.src.profile.model.PatchProfileResponse
import com.iame.qnnect.android.src.profile.model.ProfileDataModel
import com.iame.qnnect.android.src.profile.service.ProfileAPI
import com.iame.qnnect.android.src.question.model.*
import io.reactivex.Single
import okhttp3.MultipartBody

class UserQuestionDataImpl(private val service: UserQuestionAllAPI) : GetUserQuestionDataModel {
    override fun getData(): Single<List<Bookmark>> {
        return service.getQuestion()
    }
}