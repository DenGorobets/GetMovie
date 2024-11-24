package com.den.gorobets.getmovie.database

import android.content.Context
import android.content.SharedPreferences

class SharedPreferencesManager(private val context: Context) {

    private val sharedPreferences: SharedPreferences by lazy {
        context.getSharedPreferences(
            DEFAULT_PREF_PATH,
            Context.MODE_PRIVATE
        )
    }

    fun saveString(path: String, value: String) {
        sharedPreferences.edit().putString(path, value).apply()
    }

    fun getString(path: String): String {
        return sharedPreferences.getString(path, "null").toString()
    }

    fun saveColorizedThemeState(value: Boolean) {
        sharedPreferences.edit().putBoolean(COLORYZED_THEME_WAY_PREF, value).apply()
    }

    fun getColorizedThemeState(): Boolean {
        return sharedPreferences.getBoolean(COLORYZED_THEME_WAY_PREF, false)
    }

    fun saveColorizedThemeSnackbarState() {
        sharedPreferences.edit().putBoolean(COLORYZED_THEME_SNACKBAR_WAY_PREF, false).apply()
    }

    fun getColorizedThemeSnackbarState(): Boolean {
        return sharedPreferences.getBoolean(COLORYZED_THEME_SNACKBAR_WAY_PREF, true)
    }

    companion object {
        val DEFAULT_PREF_PATH = "get_movie_preferences"
        val STRING_WAY_PREF = "get_movie_string_preferences"
        val COLORYZED_THEME_WAY_PREF = "get_movie_colorized_theme_preferences"
        val COLORYZED_THEME_SNACKBAR_WAY_PREF = "get_movie_colorized_theme_snackbar_preferences"
    }
}