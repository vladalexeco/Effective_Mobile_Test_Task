package com.example.effectivemobiletesttask.presentation.tickets.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.usecase.GetAllFlyOffersUseCase
import com.example.domain.util.Resource
import com.example.effectivemobiletesttask.presentation.tickets.states.AllTicketsScreenEvent
import com.example.effectivemobiletesttask.presentation.tickets.states.AllTicketsScreenState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class AllTicketsViewModel(
    private val getAllFlyOffersUseCase: GetAllFlyOffersUseCase
) : ViewModel() {

    private var _uiState = MutableStateFlow(AllTicketsScreenState())
    val uiState: StateFlow<AllTicketsScreenState> = _uiState

    fun onEvent(allTicketsScreenEvent: AllTicketsScreenEvent) {
        when(allTicketsScreenEvent) {
            AllTicketsScreenEvent.GetAllTicketsEvent -> getAllFlyOffers()
        }
    }

    private fun getAllFlyOffers() {
        viewModelScope.launch(Dispatchers.Main) {
            getAllFlyOffersUseCase().collect { resource ->
                when(resource) {
                    is Resource.Error -> {
                        Log.d("CHECK_RESPONSE", resource.networkError.toString())
                    }
                    is Resource.Success -> {
                        Log.d("RESPONSE", resource.data.toString())
                        _uiState.update { allTicketsScreenState ->
                            allTicketsScreenState.copy(
                                allTicketsList = resource.data ?: emptyList()
                            )
                        }
                    }
                }
            }
        }
    }
}