package com.iame.qnnect.android.src.reply.model

import com.iame.qnnect.android.src.diary.model.GetQuestionResponse
import io.reactivex.Single

interface GetReplyDataModel {
    fun getData(commentId: Int): Single<GetReplyResponse>
}

