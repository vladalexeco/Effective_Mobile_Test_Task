package com.example.data.network.dto.response

import com.example.data.network.dto.model.MusicalFlyOfferDto
import com.google.gson.annotations.SerializedName

data class MusicalFlyOffersResponse(
    @SerializedName("musical_fly_offers")
    val musicalFlyOffers: List<MusicalFlyOfferDto>
) : Response()

