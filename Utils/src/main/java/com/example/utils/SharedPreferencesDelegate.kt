package com.example.utils

import android.content.SharedPreferences
import java.lang.Exception
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

class SharedPreferencesDelegate<T>(
    private val sharedPreferences: SharedPreferences,
    private val key: String,
    private val defaultVal: T
) : ReadWriteProperty<Any, T> {

    override fun getValue(thisRef: Any, property: KProperty<*>): T =
        with(sharedPreferences) {
            return when (defaultVal) {
                is String -> (getString(key, defaultVal) as T) ?: defaultVal
                is Boolean -> (getBoolean(key, defaultVal) as T) ?: defaultVal
                is Float -> (getFloat(key, defaultVal) as T) ?: defaultVal
                is Int -> (getInt(key, defaultVal) as T) ?: defaultVal
                is Long -> (getLong(key, defaultVal) as T) ?: defaultVal
                else -> throw Exception("Unsupported 'get' type")
            }
        }

    override fun setValue(thisRef: Any, property: KProperty<*>, value: T) {
        with(sharedPreferences.edit()) {
            when (value) {
                is String -> putString(key, value)
                is Boolean -> putBoolean(key, value)
                is Float -> putFloat(key, value)
                is Int -> putInt(key, value)
                is Long -> putLong(key, value)
                else -> throw Exception("Unsupported 'put' type value = " + value.toString())
            }
            apply()
        }
    }
}