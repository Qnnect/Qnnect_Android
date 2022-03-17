package com.iame.qnnect.android.viewmodel

import android.graphics.Color
import android.widget.TextView
import com.iame.qnnect.android.R
import com.iame.qnnect.android.base.BaseViewModel
import com.iame.qnnect.android.util.*

class StoreViewModel() : BaseViewModel() {

    private val TAG = "StoreViewModel"

    fun recipe_click(select: TextView, item1: TextView, item2: TextView, item3: TextView){
        select.setBackgroundResource(R.drawable.store_recipe_select_custom)

        item1.setBackgroundResource(R.drawable.store_recipe_custom)
        item1.setTextColor(Color.parseColor("#333333"))
        item2.setBackgroundResource(R.drawable.store_recipe_custom)
        item2.setTextColor(Color.parseColor("#333333"))
        item3.setBackgroundResource(R.drawable.store_recipe_custom)
        item3.setTextColor(Color.parseColor("#333333"))
    }
}