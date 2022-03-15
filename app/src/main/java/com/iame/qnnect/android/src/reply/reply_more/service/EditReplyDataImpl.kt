package com.iame.qnnect.android.src.reply.reply_more.service

import com.iame.qnnect.android.src.login.model.LoginDataModel
import com.iame.qnnect.android.src.login.model.PostLoginRequest
import com.iame.qnnect.android.src.login.model.PostLoginResponse
import com.iame.qnnect.android.src.profile.model.PatchProfileResponse
import com.iame.qnnect.android.src.profile.model.ProfileDataModel
import com.iame.qnnect.android.src.profile.service.ProfileAPI
import com.iame.qnnect.android.src.question.model.PostQuestionDataModel
import com.iame.qnnect.android.src.question.model.PostQuestionRequest
import com.iame.qnnect.android.src.reply.reply_more.model.EditReplyDataModel
import io.reactivex.Single
import okhttp3.MultipartBody

class EditReplyDataImpl(private val service: EditReplyAPI) : EditReplyDataModel {
    override fun getData(commentId: Int, replyId: Int, content: String): Single<Unit> {
        return service.editReply(commentId, replyId, content)
    }
}