package com.example.data.network

import com.example.data.network.dto.Request
import com.example.data.network.dto.response.Response

interface NetworkFlyOffersClient {

    suspend fun doRequest(dto: Request): Response
}