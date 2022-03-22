package com.iame.qnnect.android.util

import com.iame.qnnect.android.R
import com.iame.qnnect.android.src.add_drink.drink

fun Getdrink(num: Int): drink {
    var drink: drink? = null
    drink = when (num) {
        1 -> drink(1, "딸기라떼", R.mipmap.drink_strawberry_latte_foreground)
        2 -> drink(2, "레몬에이드", R.mipmap.drink_lemon_tea_foreground)
        3 -> drink(3, "민트초코", R.mipmap.drink_mintchocolate_foreground)
        4 -> drink(4, "초코라떼", R.mipmap.drink_chocolate_latte_foreground)
        else -> drink(5, "썸머라떼", R.mipmap.drink_summer_latte_foreground)
    }
    return drink!!
}

fun GetdrinkName(name: String): drink {
    var drink: drink? = null
    drink = when (name) {
        "딸기라떼" -> drink(1, "딸기라떼", R.mipmap.drink_strawberry_latte_foreground)
        "레몬에이드" -> drink(2, "레몬에이드", R.mipmap.drink_lemon_tea_foreground)
        "민트초코" -> drink(3, "민트초코", R.mipmap.drink_mintchocolate_foreground)
        "초코라떼" -> drink(4, "초코라떼", R.mipmap.drink_chocolate_latte_foreground)
        else -> drink(5, "썸머라떼", R.mipmap.drink_summer_latte_foreground)
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
            else if(recipe_name == "메인"){R.mipmap.complete_drink_main_straw_foreground}
            else if(recipe_name == "토핑"){R.mipmap.complete_drink_topping_straw_foreground}
            else{R.mipmap.complete_drink_straw_foreground}
        }
        // 민트 초코
        2 -> {
            if(recipe_name == "빈잔"){R.mipmap.complete_drink_default_foreground}
            else if(recipe_name == "얼음"){R.mipmap.complete_drink_ice_foreground}
            else if(recipe_name == "베이스"){R.mipmap.complete_drink_base_lemon_foreground}
            else if(recipe_name == "메인"){R.mipmap.complete_drink_main_lemon_foreground}
            else if(recipe_name == "토핑"){R.mipmap.complete_drink_topping_lemon_foreground}
            else{R.mipmap.complete_drink_lemon_foreground}
        }
        // 썸머 라떼
        3 -> {
            if(recipe_name == "빈잔"){R.mipmap.complete_drink_default_foreground}
            else if(recipe_name == "얼음"){R.mipmap.complete_drink_ice_foreground}
            else if(recipe_name == "베이스"){R.mipmap.complete_drink_base_foreground}
            else if(recipe_name == "메인"){R.mipmap.complete_drink_main_mint_foreground}
            else if(recipe_name == "토핑"){R.mipmap.complete_drink_topping_mint_foreground}
            else{R.mipmap.complete_drink_mint_foreground}
        }
        // 초코 라떼
        4 -> {
            if(recipe_name == "빈잔"){R.mipmap.complete_drink_default_foreground}
            else if(recipe_name == "얼음"){R.mipmap.complete_drink_ice_foreground}
            else if(recipe_name == "베이스"){R.mipmap.complete_drink_base_foreground}
            else if(recipe_name == "메인"){R.mipmap.complete_drink_main_choco_foreground}
            else if(recipe_name == "토핑"){R.mipmap.complete_drink_topping_choco_foreground}
            else{R.mipmap.complete_drink_choco_foreground}
        }
        // 레몬 에이드
        else -> {
            if(recipe_name == "빈잔"){R.mipmap.complete_drink_default_foreground}
            else if(recipe_name == "얼음"){R.mipmap.complete_drink_ice_foreground}
            else if(recipe_name == "베이스"){R.mipmap.complete_drink_base_foreground}
            else if(recipe_name == "메인"){R.mipmap.complete_drink_main_summer_foreground}
            else if(recipe_name == "토핑"){R.mipmap.complete_drink_topping_summer_foreground}
            else{R.mipmap.complete_drink_summer_foreground}
        }
    }
    return drink!!
}

fun drink_imgName(drinkName: String, recipe_name: String): Int {
    var drink: Int? = null
    drink = when (drinkName) {
        // 딸기 라떼
        "딸기라떼" -> {
            if(recipe_name == "빈잔"){R.mipmap.complete_drink_default_foreground}
            else if(recipe_name == "얼음"){R.mipmap.complete_drink_ice_foreground}
            else if(recipe_name == "우유"){R.mipmap.complete_drink_base_foreground}
            else if(recipe_name == "딸기퓨레"){R.mipmap.complete_drink_main_straw_foreground}
            else if(recipe_name == "딸기"){R.mipmap.complete_drink_topping_straw_foreground}
            else{R.mipmap.complete_drink_straw_foreground}
        }
        // 민트 초코
        "레몬에이드" -> {
            if(recipe_name == "빈잔"){R.mipmap.complete_drink_default_foreground}
            else if(recipe_name == "얼음"){R.mipmap.complete_drink_ice_foreground}
            else if(recipe_name == "사이다"){R.mipmap.complete_drink_base_lemon_foreground}
            else if(recipe_name == "레몬청"){R.mipmap.complete_drink_topping_lemon_foreground}
            else if(recipe_name == "레몬"){R.mipmap.complete_drink_topping_lemon_foreground}
            else{R.mipmap.complete_drink_lemon_foreground}
        }
        // 썸머 라떼
        "민트초코" -> {
            if(recipe_name == "빈잔"){R.mipmap.complete_drink_default_foreground}
            else if(recipe_name == "얼음"){R.mipmap.complete_drink_ice_foreground}
            else if(recipe_name == "우유"){R.mipmap.complete_drink_base_foreground}
            else if(recipe_name == "민트초코 파우더"){R.mipmap.complete_drink_main_mint_foreground}
            else if(recipe_name == "초코"){R.mipmap.complete_drink_topping_mint_foreground}
            else{R.mipmap.complete_drink_mint_foreground}
        }
        // 초코 라떼
        "초코라떼" -> {
            if(recipe_name == "빈잔"){R.mipmap.complete_drink_default_foreground}
            else if(recipe_name == "얼음"){R.mipmap.complete_drink_ice_foreground}
            else if(recipe_name == "우유"){R.mipmap.complete_drink_base_foreground}
            else if(recipe_name == "초코 파우더"){R.mipmap.complete_drink_main_choco_foreground}
            else if(recipe_name == "초코"){R.mipmap.complete_drink_topping_choco_foreground}
            else{R.mipmap.complete_drink_choco_foreground}
        }
        // 레몬 에이드
        else -> {
            if(recipe_name == "빈잔"){R.mipmap.complete_drink_default_foreground}
            else if(recipe_name == "얼음"){R.mipmap.complete_drink_ice_foreground}
            else if(recipe_name == "우유"){R.mipmap.complete_drink_base_foreground}
            else if(recipe_name == "에스프레소"){R.mipmap.complete_drink_main_summer_foreground}
            else if(recipe_name == "바닐라 아이스크림"){R.mipmap.complete_drink_topping_summer_foreground}
            else{R.mipmap.complete_drink_summer_foreground}
        }
    }
    return drink!!
}

