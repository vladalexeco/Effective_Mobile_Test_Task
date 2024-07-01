package com.example.effectivemobiletesttask.presentation.tickets.states

sealed class SearchTicketsScreenEvent {

    data object GetMusicalFlyOffersEvent: SearchTicketsScreenEvent()
    data object GetBestFlyOffersEvent: SearchTicketsScreenEvent()
    data class DepartureMainEditTextOnTextChangedEvent(val text: String) : SearchTicketsScreenEvent()
}