package com.example.data.storage

import android.content.Context
import android.content.SharedPreferences

class DataStorageSourceImpl(private val context: Context) : DataStorageSource {

    private val sharedPreferences: SharedPreferences = context.getSharedPreferences("DataStorage", Context.MODE_PRIVATE)

    override fun getData(key: String): String {
        return sharedPreferences.getString(key, "") ?: ""
    }

    override fun saveData(key: String, value: String) {
        with (sharedPreferences.edit()) {
            putString(key, value)
            apply()
        }
    }
}