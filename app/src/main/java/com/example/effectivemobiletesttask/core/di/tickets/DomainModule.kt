package com.example.effectivemobiletesttask.core.di.tickets

import com.example.domain.usecase.GetAllFlyOffersUseCase
import com.example.domain.usecase.GetBestFlyOffersUseCase
import com.example.domain.usecase.GetDepartureDataFromStorageUseCase
import com.example.domain.usecase.GetMusicalFlyOffersUseCase
import com.example.domain.usecase.SaveDepartureDataToStorageUseCase
import org.koin.dsl.module

val domainModule = module {

    factory<GetMusicalFlyOffersUseCase> {
        GetMusicalFlyOffersUseCase(flyOffersRepository = get())
    }

    factory<GetBestFlyOffersUseCase> {
        GetBestFlyOffersUseCase(flyOffersRepository = get())
    }

    factory<GetAllFlyOffersUseCase> {
        GetAllFlyOffersUseCase(flyOffersRepository = get())
    }

    factory<GetDepartureDataFromStorageUseCase> {
        GetDepartureDataFromStorageUseCase(dataStorageRepository = get())
    }

    factory<SaveDepartureDataToStorageUseCase> {
        SaveDepartureDataToStorageUseCase(dataStorageRepository = get())
    }
}