package com.example.domain.usecase

import com.example.domain.api.DataStorageRepository

class SaveDepartureDataToStorageUseCase(private val dataStorageRepository: DataStorageRepository) {
    operator fun invoke(key: String, value: String) {
        dataStorageRepository.saveData(key, value)
    }
}