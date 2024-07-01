package com.example.effectivemobiletesttask.presentation.tickets.states

sealed class SearchTicketsScreenEvent {

    data object GetMusicalFlyOffersEvent: SearchTicketsScreenEvent()
    data object GetBestFlyOffersEvent: SearchTicketsScreenEvent()
    data class GetDataFromStorageEvent(val key: String) : SearchTicketsScreenEvent()
    data class SaveDataToStorageEvent(val key: String, val value: String) : SearchTicketsScreenEvent()
}