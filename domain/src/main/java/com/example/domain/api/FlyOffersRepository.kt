package com.example.domain.api

import com.example.domain.model.BestFlyOffer
import com.example.domain.model.FlyOffer
import com.example.domain.model.MusicalFlyOffer
import com.example.domain.util.Resource
import kotlinx.coroutines.flow.Flow

interface FlyOffersRepository {
    suspend fun getMusicalFlyOffers(): Flow<Resource<List<MusicalFlyOffer>>>
    suspend fun getBestFlyOffers(): Flow<Resource<List<BestFlyOffer>>>
    suspend fun getAllFlyOffers(): Flow<Resource<List<FlyOffer>>>
}