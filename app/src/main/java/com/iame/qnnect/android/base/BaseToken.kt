package com.iame.qnnect.android.base

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.util.Log

class BaseToken {

    fun setAccessToken(context: Context, accessToken: String, refreshToken: String) {
        var text = context.getSharedPreferences("Qnnect", MODE_PRIVATE)
        var editor = text.edit()
        editor.putString("X-ACCESS-TOKEN", accessToken)
        editor.putString("refresh-token", refreshToken)
        editor.commit()
    }

    fun getAccessToken(context: Context): String? {
        // shared preference
        var text = context.getSharedPreferences("Qnnect", MODE_PRIVATE)
        var token = text.getString("X-ACCESS-TOKEN", null)
        return token
    }

    fun getRefreshToken(context: Context): String?{
        // shared preference
        var text = context.getSharedPreferences("Qnnect", MODE_PRIVATE)
        var token = text.getString("refresh-token", null)
        return token
    }
}