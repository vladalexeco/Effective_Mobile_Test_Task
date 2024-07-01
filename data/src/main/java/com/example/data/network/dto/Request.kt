package com.example.data.network.dto

sealed class Request {
    data object MusicalFlyOffersRequest: Request()
    data object BestFlyOffersRequest: Request()
    data object AllFlyOffersRequest: Request()
}