package com.example.domain.usecase

import com.example.domain.api.FlyOffersRepository
import com.example.domain.model.FlyOffer
import com.example.domain.util.Resource
import kotlinx.coroutines.flow.Flow

class GetAllFlyOffersUseCase(
    private val flyOffersRepository: FlyOffersRepository
) {
    suspend operator fun invoke(): Flow<Resource<List<FlyOffer>>> {
        return flyOffersRepository.getAllFlyOffers()
    }
}