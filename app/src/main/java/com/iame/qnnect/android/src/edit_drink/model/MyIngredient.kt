package com.iame.qnnect.android.src.edit_drink.model

import com.google.gson.annotations.SerializedName

data class MyIngredient(
    @SerializedName("ingredientId") val ingredientId: Int,
    @SerializedName("name") val name: String,
    @SerializedName("ingredientType") val ingredientType: String,
    @SerializedName("count") val count: Int
    )

// {"ingredientId":1,"name":"얼음","ingredientType":"ice_base","count":1}

//[
//{
//    "count": 30,
//    "ingredientId": 1,
//    "ingredientType": "토핑",
//    "name": "녹차가루"
//}
//]