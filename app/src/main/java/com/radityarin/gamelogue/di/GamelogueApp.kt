package com.radityarin.gamelogue.di

import android.app.Application
import com.chibatching.kotpref.Kotpref
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class GamelogueApp : Application() {

    override fun onCreate() {
        super.onCreate()

        Kotpref.init(this)

        startKoin {
            androidContext(this@GamelogueApp)
            modules(
                listOf(
                    databaseModule,
                    networkModule,
                    repositoryModule,
                    viewModelModule,
                    useCaseModule
                )
            )
        }
    }
}