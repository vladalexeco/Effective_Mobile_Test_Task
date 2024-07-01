package com.example.effectivemobiletesttask.core

import android.app.Application
import com.example.effectivemobiletesttask.core.di.tickets.dataModule
import com.example.effectivemobiletesttask.core.di.tickets.domainModule
import com.example.effectivemobiletesttask.core.di.tickets.presentationModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(
                dataModule,
                domainModule,
                presentationModule
            )
        }
    }
}