package com.iame.qnnect.android.viewmodel

import android.graphics.Color
import android.media.Image
import android.widget.ImageView
import android.widget.TextView
import com.iame.qnnect.android.R
import com.iame.qnnect.android.base.BaseViewModel

class GroupBottomViewModel() : BaseViewModel() {

    private val TAG = "GroupBottomViewModel"
    var nameCheck = false

    fun group_select(select: TextView, item1:TextView, item2: TextView){
        item1.setBackgroundResource(R.drawable.group_select_fail)
        item1.setTextColor(Color.parseColor("#333333"))
        item2.setBackgroundResource(R.drawable.group_select_fail)
        item2.setTextColor(Color.parseColor("#333333"))

        select.setBackgroundResource(R.drawable.group_select_ok)
        select.setTextColor(Color.parseColor("#FFFFFF"))
    }

    // color check
    // image view
//    color_orange_btn.setOnClickListener {
//        color_brown_btn.setBackgroundResource(R.drawable.color_in_custom)
//        color_orange_btn.setBackgroundResource(R.drawable.color_in_custom_select)
//        color_pink_btn.setBackgroundResource(R.drawable.color_in_custom)
//        color_sky_btn.setBackgroundResource(R.drawable.color_in_custom)
//        color_yellow_btn.setBackgroundResource(R.drawable.color_in_custom)
//    }

    fun color_select(select: ImageView, item1: ImageView, item2: ImageView, item3: ImageView, item4: ImageView){
        select.setBackgroundResource(R.drawable.color_in_custom_select)

        item1.setBackgroundResource(R.drawable.color_in_custom)
        item2.setBackgroundResource(R.drawable.color_in_custom)
        item3.setBackgroundResource(R.drawable.color_in_custom)
        item4.setBackgroundResource(R.drawable.color_in_custom)
    }
}