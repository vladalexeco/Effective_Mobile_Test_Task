package com.example.data.network

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.util.Log
import com.example.data.network.dto.Request
import com.example.data.network.dto.response.Response
import com.example.data.util.STATUS_CODE_NO_NETWORK_CONNECTION
import com.example.data.util.STATUS_CODE_SERVER_ERROR
import com.example.data.util.STATUS_CODE_SUCCESS
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class NetworkFlyOffersClientImpl(
    private val context: Context,
    private val flyOfferService: FlyOffersApi
) : NetworkFlyOffersClient {
    override suspend fun doRequest(dto: Request): Response {

        if (!isConnected()) {
            return Response().apply { resultCode = STATUS_CODE_NO_NETWORK_CONNECTION }
        }

        return withContext(Dispatchers.IO) {
            when (dto) {
                is Request.MusicalFlyOffersRequest -> getMusicalFlyOffers()
                Request.BestFlyOffersRequest -> getBestFlyOffers()
                Request.AllFlyOffersRequest -> getAllFlyOffers()
            }
        }
    }

    private suspend fun getMusicalFlyOffers(): Response {
        return try {
            val response = flyOfferService.getMusicalFlyOffers()
            response.apply { resultCode = STATUS_CODE_SUCCESS }
        } catch (e: Throwable) {
            Log.d("Check_Resource", e.message.toString())
            Response().apply { resultCode = STATUS_CODE_SERVER_ERROR }
        }
    }

    private suspend fun getBestFlyOffers(): Response {
        return try {
            val response = flyOfferService.getBestFlyOffers()
            response.apply { resultCode = STATUS_CODE_SUCCESS }
        } catch (e: Throwable) {
            Response().apply { resultCode = STATUS_CODE_SERVER_ERROR }
        }
    }

    private suspend fun getAllFlyOffers(): Response {
        return try {
            val response = flyOfferService.getAllFlyOffers()
            response.apply { resultCode = STATUS_CODE_SUCCESS }
        } catch (e: Throwable) {
            Response().apply { resultCode = STATUS_CODE_SERVER_ERROR }
        }
    }

    private fun isConnected(): Boolean {
        val connectivityManager = context.getSystemService(
            Context.CONNECTIVITY_SERVICE
        ) as ConnectivityManager
        val capabilities =
            connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
        return capabilities?.run {
            hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) ||
                    hasTransport(NetworkCapabilities.TRANSPORT_WIFI) ||
                    hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)
        } ?: false
    }
}