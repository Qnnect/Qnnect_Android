package com.iame.qnnect.android.src.reply.service

import com.iame.qnnect.android.src.diary.model.GetQuestionResponse
import com.iame.qnnect.android.src.login.model.PostLoginRequest
import com.iame.qnnect.android.src.login.model.PostLoginResponse
import com.iame.qnnect.android.src.reply.model.GetReplyResponse
import io.reactivex.Single
import retrofit2.Call
import retrofit2.http.*
import retrofit2.http.POST


interface GetReplyAPI {
    @GET("/api/v1/comments/{commentId}")
    fun getReply(@Path("commentId") commentId: Int) : Single<GetReplyResponse>
}