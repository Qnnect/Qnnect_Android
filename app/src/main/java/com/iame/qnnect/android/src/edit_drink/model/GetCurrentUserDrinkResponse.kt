package com.iame.qnnect.android.src.edit_drink.model

import com.google.gson.annotations.SerializedName

data class GetCurrentUserDrinkResponse(
    @SerializedName("currentDrinkInfo") var currentDrinkInfo: CurrentDrinkInfo,
    @SerializedName("myIngredient") var myIngredient: List<MyIngredient>
    )

// {
//    "currentDrinkInfo": {
//        "userDrinkSelectedId": 9,
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
//    "myIngredient": []
//}