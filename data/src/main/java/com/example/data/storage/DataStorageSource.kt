package com.example.data.storage

import android.content.SharedPreferences

interface DataStorageSource {

    fun getData(key: String): String
    fun saveData(key: String, value: String)
}