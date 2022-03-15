package com.iame.qnnect.android.src.reply.reply_more.service

import com.iame.qnnect.android.src.main.home.home_bottom.model.PostAddGroupResponse

interface DeleteReplyView{
    fun onDeleteReplySuccess(response: String?)
    fun onDeleteReplyFailure(message: String)
}