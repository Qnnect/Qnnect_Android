package com.iame.qnnect.android.src.reply.service

import com.iame.qnnect.android.src.reply.model.GetReplyResponse
import com.iame.qnnect.android.src.reply.model.PostReplyDataModel
import com.iame.qnnect.android.src.reply.model.PostReplyRequest
import io.reactivex.Single
import java.nio.ByteBuffer

class PostReplyDataImpl(private val service: PostReplyAPI) : PostReplyDataModel {
    override fun getData(commentId: Int, content: String): Single<String?> {
        return service.postReply(commentId, content)
    }
}