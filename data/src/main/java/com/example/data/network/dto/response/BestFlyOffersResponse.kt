package com.example.data.network.dto.response

import com.example.data.network.dto.model.BestFlyOfferDto
import com.google.gson.annotations.SerializedName

data class BestFlyOffersResponse(
    @SerializedName("best_fly_offers")
    val bestFlyOffers: List<BestFlyOfferDto>
) : Response()