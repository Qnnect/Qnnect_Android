package com.iame.qnnect.android.src.group.model

import com.google.gson.annotations.SerializedName

data class GetGroupResponse(
    @SerializedName("cafeId") var cafeId: Int,
    @SerializedName("cafeUserId") var cafeUserId: Int,
    @SerializedName("createdAt") var createdAt: String,
    @SerializedName("title") var title: String,
    @SerializedName("code") var code: String,
    @SerializedName("diaryColor") var diaryColor: String,
    @SerializedName("currentUserResponse") var currentUser: CafeUser,
    @SerializedName("cafeUserResponseList") var cafeUserList: List<CafeUser>,
    @SerializedName("cafeQuestionResponseList") var cafeQuestionList: List<CafeQuestion>
)

// {
// "cafeId":96,
// "cafeUserId":104,
// "createdAt":"2022-03-14",
// "title":"test",
// "code":"yH7zFxnC",
// "diaryColor":"blue",

// "currentUserResponse":
// {"user":{
// "profileImage":"https://dev-qnnect-profile.s3.ap-northeast-2.amazonaws.com/703ecd40-04e4-4a23-b78f-5433674befcd_photo.jpg",
// "nickName":"두루",
// "point":0
// },
//
// "userDrinkSelected":"딸기라떼",
// "drinkIngredientsFilledResponseList":
// [{"ingredientName":"얼음"},{"ingredientName":"얼음"}]},
// "cafeUserResponseList":[],
// "cafeQuestionResponseList":[]}