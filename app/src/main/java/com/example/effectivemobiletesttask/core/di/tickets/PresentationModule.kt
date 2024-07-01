package com.example.effectivemobiletesttask.core.di.tickets

import com.example.effectivemobiletesttask.presentation.tickets.viewmodels.AllTicketsViewModel
import com.example.effectivemobiletesttask.presentation.tickets.viewmodels.SearchTicketsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val presentationModule = module {

    viewModel<SearchTicketsViewModel> {
        SearchTicketsViewModel(
            getMusicalFlyOffersUseCase = get(),
            getBestFlyOffersUseCase = get(),
            getDepartureDataFromStorageUseCase = get(),
            saveDepartureDataToStorageUseCase = get()
        )
    }

    viewModel<AllTicketsViewModel> {
        AllTicketsViewModel(
            getAllFlyOffersUseCase = get()
        )
    }
}