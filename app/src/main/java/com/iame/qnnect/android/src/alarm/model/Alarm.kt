package com.iame.qnnect.android.src.alarm.model

import androidx.annotation.RequiresPermission
import com.google.gson.annotations.SerializedName
import com.iame.qnnect.android.src.profile.model.PatchProfileResponse
import com.kakao.sdk.user.model.User

data class Alarm(
    @SerializedName("content") val content: String,
    @SerializedName("contentId") val contentId: Int,
    @SerializedName("createdAt") val createdAt: String,
    @SerializedName("groupName") val groupName: String,
    @SerializedName("notificationId") val notificationId: Int,
    @SerializedName("notificationType") val notificationType: String,
    @SerializedName("userRead") val userRead: Boolean
    )