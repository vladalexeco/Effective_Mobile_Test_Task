package com.example.data.api

import com.example.data.network.NetworkFlyOffersClient
import com.example.data.network.dto.Request
import com.example.data.network.dto.model.mapToBestFlyOffer
import com.example.data.network.dto.model.mapToFlyOffer
import com.example.data.network.dto.model.mapToMusicalFlyOffer
import com.example.data.network.dto.response.AllFlyOffersResponse
import com.example.data.network.dto.response.BestFlyOffersResponse
import com.example.data.network.dto.response.MusicalFlyOffersResponse
import com.example.data.util.STATUS_CODE_NO_NETWORK_CONNECTION
import com.example.data.util.STATUS_CODE_SERVER_ERROR
import com.example.data.util.STATUS_CODE_SUCCESS
import com.example.domain.api.FlyOffersRepository
import com.example.domain.model.BestFlyOffer
import com.example.domain.model.FlyOffer
import com.example.domain.model.MusicalFlyOffer
import com.example.domain.util.NetworkError
import com.example.domain.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FlyOffersRepositoryImpl(
    private val networkFlyOffersClient: NetworkFlyOffersClient
) : FlyOffersRepository {

    override suspend fun getMusicalFlyOffers(): Flow<Resource<List<MusicalFlyOffer>>> = flow {
        val response = networkFlyOffersClient.doRequest(dto = Request.MusicalFlyOffersRequest)
        when (response.resultCode) {
            STATUS_CODE_NO_NETWORK_CONNECTION -> emit(Resource.Error(networkError = NetworkError.BAD_CONNECTION))
            STATUS_CODE_SUCCESS -> emit(Resource.Success((response as MusicalFlyOffersResponse)
                .musicalFlyOffers.map {
                musicalFlyOfferDto -> musicalFlyOfferDto.mapToMusicalFlyOffer()
            }))
            STATUS_CODE_SERVER_ERROR -> emit(Resource.Error(networkError = NetworkError.SERVER_ERROR))
        }
    }

    override suspend fun getBestFlyOffers(): Flow<Resource<List<BestFlyOffer>>> = flow {
        val response = networkFlyOffersClient.doRequest(dto = Request.BestFlyOffersRequest)
        when(response.resultCode) {
            STATUS_CODE_NO_NETWORK_CONNECTION -> emit(Resource.Error(networkError = NetworkError.BAD_CONNECTION))
            STATUS_CODE_SUCCESS -> emit(Resource.Success((response as BestFlyOffersResponse)
                .bestFlyOffers.map {
                        musicalFlyOfferDto -> musicalFlyOfferDto.mapToBestFlyOffer()
                }))
            STATUS_CODE_SERVER_ERROR -> emit(Resource.Error(networkError = NetworkError.SERVER_ERROR))
        }
    }

    override suspend fun getAllFlyOffers(): Flow<Resource<List<FlyOffer>>> = flow {
        val response = networkFlyOffersClient.doRequest(dto = Request.AllFlyOffersRequest)
        when(response.resultCode) {
            STATUS_CODE_NO_NETWORK_CONNECTION -> emit(Resource.Error(networkError = NetworkError.BAD_CONNECTION))
            STATUS_CODE_SUCCESS -> emit(Resource.Success((response as AllFlyOffersResponse)
                .allFlyOffers.map {
                        flyOfferDto -> flyOfferDto.mapToFlyOffer()
                }))
            STATUS_CODE_SERVER_ERROR -> emit(Resource.Error(networkError = NetworkError.SERVER_ERROR))
        }
    }
}

