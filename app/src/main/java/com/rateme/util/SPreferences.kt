package com.rateme.util

import android.content.Context

class SPreferences {
    companion object {
        fun saveToken(token: String?, context: Context) {
            val preferences = context.getSharedPreferences("TOKEN", Context.MODE_PRIVATE)
            preferences.edit().putString("token", token).apply()
        }

        fun loadToken(context: Context): String? {
            val preferences = context.getSharedPreferences("TOKEN", Context.MODE_PRIVATE)
            return preferences.getString("token", "")
        }

        fun saveIdUser(id_user: String?, context: Context) {
            val preferences = context.getSharedPreferences("IDUSER", Context.MODE_PRIVATE)
            preferences.edit().putString("id_user", id_user).apply()
        }

        fun loadIdUser(context: Context): String? {
            val preferences = context.getSharedPreferences("IDUSER", Context.MODE_PRIVATE)
            return preferences.getString("id_user", "")
        }

        fun clearToken(context: Context){
            val preferences = context.getSharedPreferences("TOKEN", 0)
            preferences.edit().remove("token").apply()
        }

        fun clearUserId(context: Context){
            val preferences = context.getSharedPreferences("IDUSER", 0)
            preferences.edit().remove("id_user").apply()
        }
    }
}