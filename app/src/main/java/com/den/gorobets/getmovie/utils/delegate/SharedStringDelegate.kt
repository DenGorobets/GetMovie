package com.den.gorobets.getmovie.utils.delegate

import android.content.Context
import android.content.Context.MODE_PRIVATE
import com.den.gorobets.getmovie.database.SharedPreferencesManager.Companion.DEFAULT_PREF_PATH
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

class SharedStringDelegate(
    private val context: Context,
    private val propertyName: String,
    private val defaultValue: String = ""
) : ReadWriteProperty<Any?, String> {

    private val sharedPreferences by lazy {
        context.getSharedPreferences(DEFAULT_PREF_PATH, MODE_PRIVATE)
    }

    override fun getValue(thisRef: Any?, property: KProperty<*>): String {
        return sharedPreferences.getString(propertyName, defaultValue) ?: defaultValue
    }

    override fun setValue(thisRef: Any?, property: KProperty<*>, value: String) {
        sharedPreferences.edit().putString(propertyName, value).apply()
    }
}

fun Context.sharedString(name: String): SharedStringDelegate =
    SharedStringDelegate(this, name)

class SharedBooleanDelegate(
    private val context: Context,
    private val propertyName: String,
    private val defaultValue: Boolean = false
) : ReadWriteProperty<Any?, Boolean> {

    private val sharedPreferences by lazy {
        context.getSharedPreferences(DEFAULT_PREF_PATH, MODE_PRIVATE)
    }

    override fun getValue(thisRef: Any?, property: KProperty<*>): Boolean {
        return sharedPreferences.getBoolean(propertyName, defaultValue)
    }

    override fun setValue(thisRef: Any?, property: KProperty<*>, value: Boolean) {
        sharedPreferences.edit().putBoolean(propertyName, value).apply()
    }
}

fun Context.sharedBool(name: String): SharedBooleanDelegate =
    SharedBooleanDelegate(this, name)