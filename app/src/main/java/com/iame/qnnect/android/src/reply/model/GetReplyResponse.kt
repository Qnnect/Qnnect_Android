package com.iame.qnnect.android.src.reply.model

import com.google.gson.annotations.SerializedName
import com.iame.qnnect.android.src.diary.model.Comments
import com.iame.qnnect.android.src.diary.model.QuestionMain

data class GetReplyResponse(
    @SerializedName("commentId") var commentId: Int,
    @SerializedName("content") var content: String,
    @SerializedName("createdAt") var createdAt: String,
    @SerializedName("imageUrl1") var imageUrl1: String?,
    @SerializedName("imageUrl2") var imageUrl2: String?,
    @SerializedName("imageUrl3") var imageUrl3: String?,
    @SerializedName("imageUrl4") var imageUrl4: String?,
    @SerializedName("imageUrl5") var imageUrl5: String?,
    @SerializedName("replies") var replies: List<Replies>,
    @SerializedName("writer") var writer: Writer
    )