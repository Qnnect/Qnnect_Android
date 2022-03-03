package com.iame.qnnect.android.src.profile.model

import com.google.gson.annotations.SerializedName
import java.io.File

data class PatchProfileRequest(
    @SerializedName("profilePicture") val profilePicture: File,
    @SerializedName("nickName") val nickName: String
)