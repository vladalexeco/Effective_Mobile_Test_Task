package com.example.domain.usecase

import com.example.domain.api.FlyOffersRepository
import com.example.domain.model.BestFlyOffer
import com.example.domain.util.Resource
import kotlinx.coroutines.flow.Flow

class GetBestFlyOffersUseCase(
    private val flyOffersRepository: FlyOffersRepository
) {
    suspend operator fun invoke(): Flow<Resource<List<BestFlyOffer>>> {
        return flyOffersRepository.getBestFlyOffers()
    }
}