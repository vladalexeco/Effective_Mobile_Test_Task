package com.example.effectivemobiletesttask.presentation.tickets.states

import com.example.domain.model.BestFlyOffer
import com.example.domain.model.MusicalFlyOffer

data class SearchTicketsScreenState(
    val musicalTicketsList: List<MusicalFlyOffer> = emptyList(),
    val bestTicketsList: List<BestFlyOffer> = emptyList(),
    val departureMainText: String = ""
) {
}