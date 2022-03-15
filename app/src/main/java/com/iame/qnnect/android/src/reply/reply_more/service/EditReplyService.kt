package com.iame.qnnect.android.src.reply.reply_more.service

import com.iame.qnnect.android.MyApplication
import com.iame.qnnect.android.src.main.home.home_bottom.model.PostAddGroupRequest
import com.iame.qnnect.android.src.main.home.home_bottom.model.PostAddGroupResponse
import com.iame.qnnect.android.src.main.home.home_bottom.service.AddGroupAPI
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class EditReplyService(val view: EditReplyView) {
    fun tryEditReply(commentId: Int, replyId: Int, content: String){
        val editreplyAPI = MyApplication.sRetrofit.create(EditReplyAPI::class.java)
        editreplyAPI.editReply(commentId, replyId, content).enqueue(object :
            Callback<String?> {
            override fun onResponse(call: Call<String?>, response: Response<String?>) {
                view.onEditReplySuccess("200 OK")
            }

            override fun onFailure(call: Call<String?>, t: Throwable) {
                view.onEditReplyFailure(t.message ?: "통신 오류")
            }
        })
    }
}

// @Path("commentId") commentId:Int,
//                  @Path("replyId") replyId:Int,
//                  @Body content: String