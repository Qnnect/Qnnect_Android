package com.iame.qnnect.android.src.profile.model

import com.google.gson.annotations.SerializedName
import java.io.File

data class PatchProfileRequest(
    @SerializedName("nick name") val nick_name: String,
    @SerializedName("profile Pricture") val profile_Pricture: File
)