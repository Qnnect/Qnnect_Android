package com.iame.qnnect.android.src.diary.model

import io.reactivex.Single

interface DeleteScrapDataModel {
    fun getData(cafeQuestionId: Int): Single<Unit>
}

