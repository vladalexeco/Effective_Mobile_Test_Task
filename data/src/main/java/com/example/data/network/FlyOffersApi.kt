package com.example.data.network

import com.example.data.network.dto.response.AllFlyOffersResponse
import com.example.data.network.dto.response.BestFlyOffersResponse
import com.example.data.network.dto.response.MusicalFlyOffersResponse
import retrofit2.http.GET

interface FlyOffersApi {

    @GET("/v3/9c04c44d-a5d2-4051-bcad-42b40ae04163")
    suspend fun getMusicalFlyOffers(): MusicalFlyOffersResponse

    @GET("/v3/f06f32cb-e134-4b8f-9929-566cd4ec7362")
    suspend fun getBestFlyOffers(): BestFlyOffersResponse

    @GET("/v3/2a33ff83-e592-4dcd-9daa-e8f03620e0ee")
    suspend fun getAllFlyOffers(): AllFlyOffersResponse
}