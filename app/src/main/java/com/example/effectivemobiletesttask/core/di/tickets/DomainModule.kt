package com.example.effectivemobiletesttask.core.di.tickets

import com.example.domain.usecase.GetAllFlyOffersUseCase
import com.example.domain.usecase.GetBestFlyOffersUseCase
import com.example.domain.usecase.GetMusicalFlyOffersUseCase
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
}