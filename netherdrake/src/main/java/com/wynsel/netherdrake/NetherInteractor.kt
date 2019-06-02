package com.wynsel.netherdrake

import android.content.SharedPreferences
import android.preference.PreferenceManager

abstract class NetherInteractor<P: NetherContracts.Presenter>(protected var presenter: P?):
    NetherContracts.Interactor {
    override fun unregister() {
        presenter = null
    }

    inline fun <reified T> SharedPreferences.get(key: String, default: T): T {
        when(T::class) {
            Boolean::class -> return this.getBoolean(key, default as Boolean) as T
            Float::class -> return this.getFloat(key, default as Float) as T
            Int::class -> return this.getInt(key, default as Int) as T
            Long::class -> return this.getLong(key, default as Long) as T
            String::class -> return this.getString(key, default as String) as T
            else -> {
                if (default is Set<*>) {
                    return this.getStringSet(key, default as Set<String>) as T
                }
            }
        }
        return default
    }

    inline fun <reified T> SharedPreferences.put(key: String, value: T) {
        val editor = this.edit()

        when(T::class) {
            Boolean::class -> editor.putBoolean(key, value as Boolean)
            Float::class -> editor.putFloat(key, value as Float)
            Int::class -> editor.putInt(key, value as Int)
            Long::class -> editor.putLong(key, value as Long)
            String::class -> editor.putString(key, value as String)
            else -> {
                if (value is Set<*>) {
                    editor.putStringSet(key, value as Set<String>)
                }
            }
        }

        editor.apply()
    }
}