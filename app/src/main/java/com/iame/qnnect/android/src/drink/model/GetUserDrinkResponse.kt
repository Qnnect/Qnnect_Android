package com.iame.qnnect.android.src.drink.model

import com.google.gson.annotations.SerializedName
import com.iame.qnnect.android.src.edit_drink.model.CurrentDrinkInfo
import com.iame.qnnect.android.src.edit_drink.model.MyIngredient

data class GetUserDrinkResponse(
    @SerializedName("cafeUsers") var cafeUsers: List<CafeUser>,
    @SerializedName("currentDrinkInfo") var currentDrinkInfo: CurrentDrinkInfo,
    @SerializedName("currentUser") var currentUser: Boolean
    )