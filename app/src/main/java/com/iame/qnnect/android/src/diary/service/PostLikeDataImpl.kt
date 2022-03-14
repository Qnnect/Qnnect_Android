package com.iame.qnnect.android.src.diary.service

import com.iame.qnnect.android.src.diary.model.PostLikeDataModel
import com.iame.qnnect.android.src.diary.model.PostScrapDataModel
import com.iame.qnnect.android.src.login.model.LoginDataModel
import com.iame.qnnect.android.src.login.model.PostLoginRequest
import com.iame.qnnect.android.src.login.model.PostLoginResponse
import io.reactivex.Single

class PostLikeDataImpl(private val service: PostLikeAPI) : PostLikeDataModel {
    override fun getData(cafeQuestionId: Int, isUserLiked: Boolean): Single<Unit> {
        return service.postLike(cafeQuestionId, isUserLiked)
    }
}