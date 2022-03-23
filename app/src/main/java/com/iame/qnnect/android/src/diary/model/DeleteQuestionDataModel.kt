package com.iame.qnnect.android.src.diary.model

import io.reactivex.Single

interface DeleteQuestionDataModel {
    fun getData(cafeQuestionId: Int): Single<Unit>
}

