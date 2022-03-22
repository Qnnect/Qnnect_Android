package com.iame.qnnect.android.src.edit_drink.model

import com.google.gson.annotations.SerializedName

data class CurrentDrinkInfo(
    @SerializedName("userDrinkSelectedId") val userDrinkSelectedId: Int,
    @SerializedName("userDrinkName") val userDrinkName: String,
    @SerializedName("currentDrinkIngredientsFilled") val currentDrinkIngredientsFilled: List<CurrentDrinkFilled>,
    @SerializedName("ice") val ice: Int,
    @SerializedName("iceFilled") val iceFilled: Int,
    @SerializedName("base") val base: Int,
    @SerializedName("baseFilled") val baseFilled: Int,
    @SerializedName("main") val main: Int,
    @SerializedName("mainFilled") val mainFilled: Int,
    @SerializedName("topping") val topping: Int,
    @SerializedName("toppingFilled") val toppingFilled: Int,
    )

//        "userDrinkSelectedId": 30,
//        "userDrinkName": "딸기라떼",
//        "currentDrinkIngredientsFilled": [
//            {
//                "ingredientName": "얼음"
//            },
//            {
//                "ingredientName": "얼음"
//            },
//            {
//                "ingredientName": "우유"
//            },
//            {
//                "ingredientName": "우유"
//            },
//            {
//                "ingredientName": "우유"
//            },
//            {
//                "ingredientName": "딸기퓨레"
//            },
//            {
//                "ingredientName": "딸기퓨레"
//            },
//            {
//                "ingredientName": "딸기퓨레"
//            },
//            {
//                "ingredientName": "딸기"
//            },
//            {
//                "ingredientName": "딸기"
//            }
//        ],
//        "ice": 2,
//        "iceFilled": 2,
//        "base": 3,
//        "baseFilled": 3,
//        "main": 3,
//        "mainFilled": 3,
//        "topping": 2,
//        "toppingFilled": 2