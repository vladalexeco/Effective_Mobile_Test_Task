package com.example.domain.usecase

import com.example.domain.api.DataStorageRepository

class GetDepartureDataFromStorageUseCase(private val dataStorageRepository: DataStorageRepository) {
    operator fun invoke(key: String): String {
        return dataStorageRepository.getData(key)
    }
}