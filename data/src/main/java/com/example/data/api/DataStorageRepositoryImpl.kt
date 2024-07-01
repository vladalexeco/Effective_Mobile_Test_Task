package com.example.data.api

import com.example.data.storage.DataStorageSource
import com.example.domain.api.DataStorageRepository

class DataStorageRepositoryImpl(private val dataStorageSource: DataStorageSource) : DataStorageRepository{
    override fun getData(key: String): String {
        return dataStorageSource.getData(key)
    }

    override fun saveData(key: String, value: String) {
        dataStorageSource.saveData(key, value)
    }
}