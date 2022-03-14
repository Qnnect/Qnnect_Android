package com.iame.qnnect.android.src.diary.model

import io.reactivex.Single

interface PostLikeDataModel {
    fun getData(cafeQuestionId: Int, isUserLiked: Boolean): Single<Unit>
}

