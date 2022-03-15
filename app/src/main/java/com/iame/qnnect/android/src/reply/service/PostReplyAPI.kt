package com.iame.qnnect.android.src.reply.service

import com.iame.qnnect.android.src.diary.model.GetQuestionResponse
import com.iame.qnnect.android.src.login.model.PostLoginRequest
import com.iame.qnnect.android.src.login.model.PostLoginResponse
import com.iame.qnnect.android.src.reply.model.GetReplyResponse
import com.iame.qnnect.android.src.reply.model.PostReplyRequest
import io.reactivex.Single
import org.w3c.dom.Text
import retrofit2.http.*
import java.nio.ByteBuffer

interface PostReplyAPI {
    @POST("/api/v1/comments/{commentId}/reply")
    fun postReply(@Path("commentId") commentId: Int, @Body params: String) : Single<String?>
}