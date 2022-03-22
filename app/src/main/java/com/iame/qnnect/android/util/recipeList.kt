package com.iame.qnnect.android.util

import com.iame.qnnect.android.R
import com.iame.qnnect.android.src.add_drink.drink

data class recipe(
    var index: Int,
    var name: String,
    var img: Int,
    var point: Int
)

fun recipe(num: Int): recipe {
    var recipelist: recipe? = null
    recipelist = when (num) {
        1 -> recipe(1, "얼음", R.mipmap.img_ice_edit_foreground, 10)
        2 -> recipe(2, "우유", R.mipmap.img_milk_foreground, 30)
        3 -> recipe(3, "사이다", R.mipmap.img_ciedar_foreground, 20)
        4 -> recipe(4, "딸기퓨래", R.mipmap.img_straw_foreground, 50)
        5 -> recipe(5, "레몬청", R.mipmap.img_lemon_foreground, 50)
        6 -> recipe(6, "민트 초코 파우더", R.mipmap.img_mint_foreground, 40)
        7 -> recipe(7, "초코 파우더", R.mipmap.img_cocoa_foreground, 40)
        8 -> recipe(8, "에스프레스 1샷", R.mipmap.img_espresso_foreground, 40)
        9 -> recipe(9, "딸기", R.mipmap.img_fruit_straw_foreground, 15)
        10 -> recipe(10, "레몬", R.mipmap.img_fruit_lemon_foreground, 15)
        11 -> recipe(11, "초코", R.mipmap.img_chocolate_foreground, 15)
        else-> recipe(12, "바닐라 아이스크림", R.mipmap.img_vanilla_foreground, 25)

    }
    return recipelist!!
}


fun allrecipe(): ArrayList<recipe> {
    var recipelist = ArrayList<recipe>()
    for(i in 0..11){
        recipelist.add(recipe(i+1))
    }
    return recipelist!!
}

fun baserecipe(): ArrayList<recipe> {
    var recipelist = ArrayList<recipe>()
    recipelist.add(recipe(1))
    recipelist.add(recipe(2))
    recipelist.add(recipe(3))
    return recipelist!!
}

fun mainrecipe(): ArrayList<recipe> {
    var recipelist = ArrayList<recipe>()
    recipelist.add(recipe(4))
    recipelist.add(recipe(5))
    recipelist.add(recipe(6))
    recipelist.add(recipe(7))
    return recipelist!!
}

fun toppingrecipe(): ArrayList<recipe> {
    var recipelist = ArrayList<recipe>()
    recipelist.add(recipe(8))
    recipelist.add(recipe(9))
    recipelist.add(recipe(10))
    recipelist.add(recipe(11))
    recipelist.add(recipe(12))
    return recipelist!!
}