package com.iame.qnnect.android.src.edit_drink.model

import com.google.gson.annotations.SerializedName

data class GetCurrentUserDrinkResponse(
    @SerializedName("currentDrinkInfo") val currentDrinkInfo: CurrentDrinkInfo,
    @SerializedName("myIngredient") val myIngredient: List<MyIngredient>
    )

// {
//    "currentDrinkInfo": {
//        "userDrinkSelectedId": 46,
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
//    "myIngredient": [
//        {
//            "ingredientId": 11,
//            "name": "초코",
//            "ingredientType": "topping",
//            "count": 1
//        },
//        {
//            "ingredientId": 12,
//            "name": "바닐라 아이스크림",
//            "ingredientType": "topping",
//            "count": 1
//        },
//        {
//            "ingredientId": 5,
//            "name": "레몬청",
//            "ingredientType": "main",
//            "count": 3
//        }
//    ]
//}