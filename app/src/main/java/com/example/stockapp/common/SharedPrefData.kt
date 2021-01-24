/*
 * Copyright (c) 2019. Innoplexus Consulting Services Pvt. Ltd.
 * All rights reserved.
 */

package com.example.stockapp.common

import android.content.Context
import android.content.SharedPreferences
import com.example.stockapp.App

object SharedPrefData {

    const val SORT_VALUE="sort_value"
    private fun getPref(): SharedPreferences {
        return App.instance.getSharedPreferences("stockapp", Context.MODE_PRIVATE)
    }

    /**
     *  set and get string preferences
     */
    fun setString(key: String, value: String) {
        getPref().edit().putString(key, value).commit()
    }

    //fun without default value as parameter
    fun getString(key: String): String? {
        return getPref().getString(key, "default")
    }

    //fun with default value as parameter
    fun getString(key: String, defaultValue: String): String? {
        return getPref().getString(key, defaultValue)
    }

    /**
     *  set and get boolean preferences
     */
    fun setBoolean(key: String, value: Boolean) {
        getPref().edit().putBoolean(key, value).commit()
    }

    //fun with default value as parameter
    fun getBoolean(key: String, defaultValue: Boolean): Boolean {
        return getPref().getBoolean(key, defaultValue)
    }

    /**
     *  set and get int preferences
     */
    fun setInt(key: String, value: Int) {
        getPref().edit().putInt(key, value).commit()
    }

    //fun without default value
    fun getInt(key: String): Int {
        return getPref().getInt(key, -1)
    }

    //fun with default value as parameter
    fun getInt(key: String, defaultValue: Int): Int {
        return getPref().getInt(key, defaultValue)
    }
}