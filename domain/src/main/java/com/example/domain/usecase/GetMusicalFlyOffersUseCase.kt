package com.example.domain.usecase

import com.example.domain.api.FlyOffersRepository
import com.example.domain.model.MusicalFlyOffer
import com.example.domain.util.Resource
import kotlinx.coroutines.flow.Flow

class GetMusicalFlyOffersUseCase(
    private val flyOffersRepository: FlyOffersRepository
) {
    suspend operator fun invoke(): Flow<Resource<List<MusicalFlyOffer>>> {
        return flyOffersRepository.getMusicalFlyOffers()
    }
}