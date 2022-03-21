package com.iame.qnnect.android.src.drink.model

import com.google.gson.annotations.SerializedName
import com.iame.qnnect.android.src.edit_drink.model.CurrentDrinkInfo
import com.iame.qnnect.android.src.edit_drink.model.MyIngredient

data class GetUserDrinkResponse(
    @SerializedName("cafeUsers") val cafeUsers: List<CafeUser>,
    @SerializedName("currentDrinkInfo") val currentDrinkInfo: CurrentDrinkInfo,
    @SerializedName("currentUser") val currentUser: Boolean
    )

// {
//    "cafeUsers": [
//        {
//            "profileImage": "https://dev-qnnect-profile.s3.ap-northeast-2.amazonaws.com/a5d8f49c-2142-42a0-8495-822897a898b1_profilePicture",
//            "nickName": "제제로",
//            "cafeUserId": 123
//        }
//    ],
//    "currentDrinkInfo": {
//        "userDrinkSelectedId": 53,
//        "userDrinkName": "딸기라떼",
//        "currentDrinkIngredientsFilled": [],
//        "ice": 2,
//        "iceFilled": 0,
//        "base": 3,
//        "baseFilled": 0,
//        "main": 3,
//        "mainFilled": 0,
//        "topping": 2,
//        "toppingFilled": 0
//    },
//    "currentUser": true
//}