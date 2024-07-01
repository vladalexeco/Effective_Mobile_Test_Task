package com.example.data.network.dto.model

import com.example.domain.model.MusicalFlyOffer
import com.google.gson.annotations.SerializedName

data class MusicalFlyOfferDto(
    val id: Int,
    @SerializedName("music_artist")
    val musicArtist: String,
    val destination: String,
    val price: Int
)

fun MusicalFlyOfferDto.mapToMusicalFlyOffer(): MusicalFlyOffer {
    return MusicalFlyOffer(
        id = this.id,
        musicArtist = this.musicArtist,
        destination = this.destination,
        price = this.price
    )
}
