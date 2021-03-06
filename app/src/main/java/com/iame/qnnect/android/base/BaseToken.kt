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

    fun setCafeCode(context: Context, cafeCode: String?) {
        var text = context.getSharedPreferences("Qnnect", MODE_PRIVATE)
        var editor = text.edit()
        editor.putString("cafeCode", cafeCode)
        editor.commit()
    }

    fun getCafeCode(context: Context): String? {
        // shared preference
        var text = context.getSharedPreferences("Qnnect", MODE_PRIVATE)
        var token = text.getString("cafeCode", null)
        return token
    }

    fun setLink(context: Context, link: Boolean) {
        var text = context.getSharedPreferences("Qnnect", MODE_PRIVATE)
        var editor = text.edit()
        editor.putBoolean("link", link!!)
        editor.commit()
    }

    fun getLink(context: Context): Boolean {
        // shared preference
        var text = context.getSharedPreferences("Qnnect", MODE_PRIVATE)
        var token = text.getBoolean("link", false)
        return token
    }

    fun setFCM(context: Context, token: String?) {
        var text = context.getSharedPreferences("Qnnect", MODE_PRIVATE)
        var editor = text.edit()
        editor.putString("FCM", token!!)
        editor.commit()
    }

    fun getFCM(context: Context): String? {
        // shared preference
        var text = context.getSharedPreferences("Qnnect", MODE_PRIVATE)
        var token = text.getString("FCM", null)
        return token
    }

    fun setAlarm(context: Context, alarm: Boolean) {
        var text = context.getSharedPreferences("Qnnect", MODE_PRIVATE)
        var editor = text.edit()
        editor.putBoolean("alarm", alarm!!)
        editor.commit()
    }

    fun getAlarm(context: Context): Boolean {
        // shared preference
        var text = context.getSharedPreferences("Qnnect", MODE_PRIVATE)
        var token = text.getBoolean("alarm", false)
        return token
    }
}