package com.iame.qnnect.android.src.reply.service

import com.iame.qnnect.android.src.diary.model.GetQuestionDataModel
import com.iame.qnnect.android.src.diary.model.GetQuestionResponse
import com.iame.qnnect.android.src.diary.model.PostScrapDataModel
import com.iame.qnnect.android.src.login.model.LoginDataModel
import com.iame.qnnect.android.src.login.model.PostLoginRequest
import com.iame.qnnect.android.src.login.model.PostLoginResponse
import com.iame.qnnect.android.src.reply.model.GetReplyDataModel
import com.iame.qnnect.android.src.reply.model.GetReplyResponse
import com.iame.qnnect.android.src.reply.model.PostReplyDataModel
import io.reactivex.Single

class PostReplyDataImpl(private val service: PostReplyAPI) : PostReplyDataModel {
    override fun getData(commentId: Int, content: String): Single<String?> {
        return service.postReply(commentId, content)
    }
}