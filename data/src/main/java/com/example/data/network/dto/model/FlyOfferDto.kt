package com.example.data.network.dto.model

import com.example.domain.model.FlyOffer
import com.google.gson.annotations.SerializedName

data class FlyOfferDto(
    val id: Int,
    val price: Int,
    @SerializedName("departure_time")
    val departureTime: String,
    @SerializedName("arrival_time")
    val arrivalTime: String,
    @SerializedName("departure_code")
    val departureCode: String,
    @SerializedName("arrival_code")
    val arrivalCode: String,
    @SerializedName("travel_time")
    val travelTime: Int,
    @SerializedName("without_transfer")
    val withoutTransfer: Boolean,
    @SerializedName("extra_condition")
    val extraCondition: String
)

fun FlyOfferDto.mapToFlyOffer(): FlyOffer {
    return FlyOffer(
        id = this.id,
        price = this.price,
        departureTime = this.departureTime,
        arrivalTime = this.arrivalTime,
        departureCode = this.departureCode,
        arrivalCode = this.arrivalCode,
        travelTime = this.travelTime,
        withoutTransfer = this.withoutTransfer,
        extraCondition = this.extraCondition
    )
}
