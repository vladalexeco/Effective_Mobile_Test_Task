package com.example.effectivemobiletesttask.presentation.tickets.states

import com.example.domain.model.FlyOffer

data class AllTicketsScreenState(
    val allTicketsList: List<FlyOffer> = emptyList()
)
