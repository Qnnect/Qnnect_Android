package com.iame.qnnect.android.src.diary.model

import io.reactivex.Single

interface PostScrapDataModel {
    fun getData(cafeQuestionId: Int): Single<Unit>
}

