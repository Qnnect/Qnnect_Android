package com.iame.qnnect.android.src.diary.model

import io.reactivex.Single

interface GetQuestionDataModel {
    fun getData(cafeQuestionId: Int): Single<GetQuestionResponse>
}

