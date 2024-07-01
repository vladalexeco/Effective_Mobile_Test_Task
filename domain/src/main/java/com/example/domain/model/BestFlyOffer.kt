package com.example.domain.model

data class BestFlyOffer(
    val id: Int,
    val aviaCompany: String,
    val price: Int,
    val color: String,
    val time: List<String>
)
