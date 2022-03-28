package com.iame.qnnect.android.src.alarm.model

import androidx.annotation.RequiresPermission
import com.google.gson.annotations.SerializedName
import com.iame.qnnect.android.src.profile.model.PatchProfileResponse
import com.kakao.sdk.user.model.User

data class Alarm(
    @SerializedName("checked") val checked: Boolean,
    @SerializedName("questionType") val questionType: Int,
    @SerializedName("groupName") val groupName: String,
    @SerializedName("contents") val contents: String,
    @SerializedName("createdAt") val createdAt: String
    )

// [
//  {
//    "content": "string",
//    "createdAt": "2022-03-28",
//    "groupName": "string",
//    "notificationId": 0,
//    "notificationType": "comment",
//    "userRead": true
//  }
//]