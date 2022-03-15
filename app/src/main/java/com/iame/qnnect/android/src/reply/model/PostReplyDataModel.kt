package com.iame.qnnect.android.src.reply.model

import com.iame.qnnect.android.src.diary.model.GetQuestionResponse
import io.reactivex.Single
import java.nio.ByteBuffer

interface PostReplyDataModel {
    fun getData(commentId: Int, content: String): Single<String?>
}

