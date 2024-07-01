package com.example.domain.api

interface DataStorageRepository {

    fun getData(key: String): String
    fun saveData(key: String, value: String)
}