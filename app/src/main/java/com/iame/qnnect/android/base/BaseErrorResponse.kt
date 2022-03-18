package com.iame.qnnect.android.base

import com.google.gson.annotations.SerializedName

data class BaseErrorResponse(
    @SerializedName("timestamp") var timestamp: String,
    @SerializedName("status") var status: Int,
    @SerializedName("error") var error: String,
    @SerializedName("code") var code: String,
    @SerializedName("message") var message: String
    )
//{
//    "accessToken": "string",
//    "refreshToken": "string"
//}

// {
// "timestamp":"2022-03-18T10:39:06.265",
// "status":406,
// "error":"NOT_ACCEPTABLE",
// "code":"WRONG_INGREDIENT_DIFFERENT_LEVEL",
// "message":"해당 주재료의 단계가 아닙니다!"
// }