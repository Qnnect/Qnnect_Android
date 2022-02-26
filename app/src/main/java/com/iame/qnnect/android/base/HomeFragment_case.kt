package com.iame.qnnect.android.base

import android.content.Context
import android.content.Context.MODE_PRIVATE

class HomeFragment_case {

    fun setHome(context: Context, home_case: Int, group_name: String) {
        var text = context.getSharedPreferences("Qnnect", MODE_PRIVATE)
        var editor = text.edit()
        editor.putInt("home_case", home_case)
        editor.putString("group_name", group_name)
        editor.commit()
    }

    fun getHomeCase(context: Context): Int? {
        // shared preference
        var text = context.getSharedPreferences("Qnnect", MODE_PRIVATE)
        var home_case = text.getInt("home_case", 0)
        return home_case
    }

    fun getGroupname(context: Context): String?{
        // shared preference
        var text = context.getSharedPreferences("Qnnect", MODE_PRIVATE)
        var group_name = text.getString("group_name", null)
        return group_name
    }
}