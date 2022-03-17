package com.iame.qnnect.android.src.edit_drink.model

import com.google.gson.annotations.SerializedName

data class CurrentDrinkFilled(
    @SerializedName("ingredientName") var ingredientName: String
    )

// {"ingredientId":1,"name":"얼음","ingredientType":"ice_base","count":1}