package com.iame.qnnect.android.src.reply.reply_more.model

import com.iame.qnnect.android.src.profile.model.PatchProfileResponse
import io.reactivex.Single
import okhttp3.MultipartBody
import retrofit2.http.Part

interface EditReplyDataModel {
    fun getData(commentId: Int, replyId: Int, content: String): Single<Unit>
}
