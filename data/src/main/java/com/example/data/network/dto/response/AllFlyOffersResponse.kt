package com.example.data.network.dto.response

import com.example.data.network.dto.model.FlyOfferDto
import com.google.gson.annotations.SerializedName

data class AllFlyOffersResponse(
    @SerializedName("all_fly_offers")
    val allFlyOffers: List<FlyOfferDto>
) : Response()