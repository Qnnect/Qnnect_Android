package com.iame.qnnect.android.src.main.home

import android.view.View

interface ItemClickListener{
    fun onClick(view: View, position: Int)
}
//를릭 리스너
lateinit var itemClickListner: ItemClickListener

fun setItemClickListener(itemClickListener: ItemClickListener) {
    itemClickListner = itemClickListener
}