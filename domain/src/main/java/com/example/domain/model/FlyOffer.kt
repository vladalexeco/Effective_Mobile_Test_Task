package com.example.domain.model

data class FlyOffer(
    val id: Int,
    val price: Int,
    val departureTime: String,
    val arrivalTime: String,
    val departureCode: String,
    val arrivalCode: String,
    val travelTime: Int,
    val withoutTransfer: Boolean,
    val extraCondition: String
)
