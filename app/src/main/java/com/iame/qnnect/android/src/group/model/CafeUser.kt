package com.iame.qnnect.android.src.group.model

import com.google.gson.annotations.SerializedName

data class CafeUser(
    @SerializedName("cafeDrinkCommonResponse") val cafeDrinkCommonResponse: CafeDrinkCommonResponse,
    @SerializedName("user") val user: User
)

// "currentUserResponse": {
////        "user": {
////            "profileImage": "https://dev-qnnect-profile.s3.ap-northeast-2.amazonaws.com/703ecd40-04e4-4a23-b78f-5433674befcd_photo.jpg",
////            "nickName": "두루",
////            "point": 155
////        },
////        "cafeDrinkCommonResponse": {
////            "userDrinkSelectedId": null,
////            "userDrinkName": null,
////            "currentDrinkIngredientsFilled": null,
////            "ice": 0,
////            "iceFilled": 0,
////            "base": 0,
////            "baseFilled": 0,
////            "main": 0,
////            "mainFilled": 0,
////            "topping": 0,
////            "toppingFilled": 0
////        }
////    }