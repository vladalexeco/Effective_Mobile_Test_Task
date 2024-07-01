package com.example.effectivemobiletesttask.presentation.tickets.states

sealed class AllTicketsScreenEvent {

    data object GetAllTicketsEvent : AllTicketsScreenEvent()
}