package com.iame.qnnect.android.src.reply.service

import com.iame.qnnect.android.src.diary.model.GetQuestionDataModel
import com.iame.qnnect.android.src.diary.model.GetQuestionResponse
import com.iame.qnnect.android.src.diary.model.PostScrapDataModel
import com.iame.qnnect.android.src.login.model.LoginDataModel
import com.iame.qnnect.android.src.login.model.PostLoginRequest
import com.iame.qnnect.android.src.login.model.PostLoginResponse
import com.iame.qnnect.android.src.reply.model.DeleteAnswerDataModel
import com.iame.qnnect.android.src.reply.model.GetReplyDataModel
import com.iame.qnnect.android.src.reply.model.GetReplyResponse
import io.reactivex.Single

class DeleteAnswerDataImpl(private val service: DeleteAnswerAPI) : DeleteAnswerDataModel {
    override fun getData(commentId: Int): Single<Unit> {
        return service.deleteAnswer(commentId)
    }
}