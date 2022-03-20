package com.iame.qnnect.android.util

import com.iame.qnnect.android.R

data class drink(
    var index: Int,
    var name: String,
    var img: Int
)

fun drink(num: Int): drink {
    var drink: drink? = null
    drink = when (num) {
        1 -> drink(1, "딸기 라떼", R.mipmap.drink_strawberry_latte_foreground)
        2 -> drink(2, "민트초코", R.mipmap.drink_mintchocolate_foreground)
        3 -> drink(3, "썸머 라떼", R.mipmap.drink_summer_latte_foreground)
        4 -> drink(4, "초코라떼", R.mipmap.drink_chocolate_latte_foreground)
        else -> drink(5, "레몬에이드", R.mipmap.drink_lemon_tea_foreground)
    }
    return drink!!
}

fun drink_img(drinkId: Int, recipe_name: String): Int {
    var drink: Int? = null
    drink = when (drinkId) {
        // 딸기 라떼
        1 -> {
            if(recipe_name == "빈잔"){R.mipmap.complete_drink_default_foreground}
            else if(recipe_name == "얼음"){R.mipmap.complete_drink_ice_foreground}
            else if(recipe_name == "베이스"){R.mipmap.complete_drink_base_foreground}
            else if(recipe_name == "토핑"){R.mipmap.complete_drink_topping_straw_foreground}
            else{R.mipmap.complete_drink_straw_foreground}
        }
        // 민트 초코
        2 -> {
            if(recipe_name == "빈잔"){R.mipmap.complete_drink_default_foreground}
            else if(recipe_name == "얼음"){R.mipmap.complete_drink_ice_foreground}
            else if(recipe_name == "베이스"){R.mipmap.complete_drink_base_foreground}
            else if(recipe_name == "토핑"){R.mipmap.complete_drink_topping_mint_foreground}
            else{R.mipmap.complete_drink_mint_foreground}
        }
        // 썸머 라떼
        3 -> {
            if(recipe_name == "빈잔"){R.mipmap.complete_drink_default_foreground}
            else if(recipe_name == "얼음"){R.mipmap.complete_drink_ice_foreground}
            else if(recipe_name == "베이스"){R.mipmap.complete_drink_base_foreground}
            else if(recipe_name == "토핑"){R.mipmap.complete_drink_topping_summer_foreground}
            else{R.mipmap.complete_drink_summer_foreground}
        }
        // 초코 라떼
        4 -> {
            if(recipe_name == "빈잔"){R.mipmap.complete_drink_default_foreground}
            else if(recipe_name == "얼음"){R.mipmap.complete_drink_ice_foreground}
            else if(recipe_name == "베이스"){R.mipmap.complete_drink_base_foreground}
            else if(recipe_name == "토핑"){R.mipmap.complete_drink_topping_choco_foreground}
            else{R.mipmap.complete_drink_choco_foreground}
        }
        // 레몬 에이드
        else -> {
            if(recipe_name == "빈잔"){R.mipmap.complete_drink_default_foreground}
            else if(recipe_name == "얼음"){R.mipmap.complete_drink_ice_foreground}
            else if(recipe_name == "베이스"){R.mipmap.complete_drink_base_lemon_foreground}
            else if(recipe_name == "토핑"){R.mipmap.complete_drink_topping_lemon_foreground}
            else{R.mipmap.complete_drink_lemon_foreground}
        }
    }
    return drink!!
}

