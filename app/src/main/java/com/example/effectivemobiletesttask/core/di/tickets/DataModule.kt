package com.example.effectivemobiletesttask.core.di.tickets

import com.example.data.api.FlyOffersRepositoryImpl
import com.example.data.network.FlyOffersApi
import com.example.data.network.NetworkFlyOffersClient
import com.example.data.network.NetworkFlyOffersClientImpl
import com.example.domain.api.FlyOffersRepository
import com.example.effectivemobiletesttask.core.util.BASE_URL
import okhttp3.OkHttpClient
import okhttp3.ResponseBody
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val dataModule = module {

    single<FlyOffersApi> {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(FlyOffersApi::class.java)
    }

    single<NetworkFlyOffersClient> {
        NetworkFlyOffersClientImpl(context = androidContext(), flyOfferService = get())
    }

    single<FlyOffersRepository> {
        FlyOffersRepositoryImpl(networkFlyOffersClient = get())
    }
}