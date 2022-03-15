package com.iame.qnnect.android.src.reply.reply_more.service

import com.iame.qnnect.android.src.main.home.home_bottom.model.PostAddGroupRequest
import com.iame.qnnect.android.src.main.home.home_bottom.model.PostAddGroupResponse
import io.reactivex.Single
import retrofit2.Call
import retrofit2.http.*

interface EditReplyAPI {
    @PATCH("/api/v1/comments/{commentId}/reply/{replyId}")
    fun editReply(@Path("commentId") commentId:Int,
                  @Path("replyId") replyId:Int,
                  @Body content: String) : Call<String?>
}