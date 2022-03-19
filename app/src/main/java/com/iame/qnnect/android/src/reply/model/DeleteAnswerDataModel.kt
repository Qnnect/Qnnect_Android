package com.iame.qnnect.android.src.reply.model

import com.iame.qnnect.android.src.diary.model.GetQuestionResponse
import io.reactivex.Single
import java.nio.ByteBuffer

interface DeleteAnswerDataModel {
    fun getData(commentId: Int): Single<Unit>
}

