package com.example.data.network.dto.model

import com.example.domain.model.BestFlyOffer
import com.google.gson.annotations.SerializedName

data class BestFlyOfferDto(
    val id: Int,
    @SerializedName("avia_company")
    val aviaCompany: String,
    val price: Int,
    val color: String,
    val time: List<String>
)

fun BestFlyOfferDto.mapToBestFlyOffer(): BestFlyOffer {
    return BestFlyOffer(
        id = this.id,
        aviaCompany = this.aviaCompany,
        price = this.price,
        color = this.color,
        time = this.time
    )
}