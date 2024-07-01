package com.example.effectivemobiletesttask.presentation.tickets.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.usecase.GetBestFlyOffersUseCase
import com.example.domain.usecase.GetMusicalFlyOffersUseCase
import com.example.domain.util.Resource
import com.example.effectivemobiletesttask.presentation.tickets.states.SearchTicketsScreenEvent
import com.example.effectivemobiletesttask.presentation.tickets.states.SearchTicketsScreenState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SearchTicketsViewModel(
    private val getMusicalFlyOffersUseCase: GetMusicalFlyOffersUseCase,
    private val getBestFlyOffersUseCase: GetBestFlyOffersUseCase
) : ViewModel() {

    private var _uiState = MutableStateFlow(SearchTicketsScreenState())
    val uiState: StateFlow<SearchTicketsScreenState> = _uiState

    fun onEvent(searchTicketsEvent: SearchTicketsScreenEvent) {
        when(searchTicketsEvent) {
            SearchTicketsScreenEvent.GetMusicalFlyOffersEvent -> getMusicalFlyOffers()
            SearchTicketsScreenEvent.GetBestFlyOffersEvent -> getBestFlyOffers()
            is SearchTicketsScreenEvent.DepartureMainEditTextOnTextChangedEvent -> {

            }
        }
    }

    private fun departureMainOnTextChangedEvent(text: String) {
        _uiState.update { searchTicketsScreenState ->
            searchTicketsScreenState.copy(
                departureMainText = text
            )
        }
    }

    private fun getMusicalFlyOffers() {
        viewModelScope.launch(Dispatchers.Main) {
            getMusicalFlyOffersUseCase().collect { resource ->
                when(resource) {
                    is Resource.Error -> {

                    }
                    is Resource.Success -> {
                        _uiState.update { searchTicketsScreenState ->
                            searchTicketsScreenState.copy(
                                musicalTicketsList = resource.data ?: emptyList()
                            )
                        }
                    }
                }
            }
        }
    }

    private fun getBestFlyOffers() {
        viewModelScope.launch(Dispatchers.Main) {
            getBestFlyOffersUseCase().collect { resource ->
                when(resource) {
                    is Resource.Error -> {
                        Log.d("CHECK_RESOURCE", resource.networkError.toString())
                    }
                    is Resource.Success -> {
                       _uiState.update { searchTicketsScreenState ->
                           searchTicketsScreenState.copy(
                               bestTicketsList = resource.data?.take(3) ?: emptyList()
                           )
                       }
                    }
                }
            }
        }
    }
}