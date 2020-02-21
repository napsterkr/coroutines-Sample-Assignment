package com.example.myassignment

import android.app.Application
import com.example.myassignment.CoinModule.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.loadKoinModules
import org.koin.core.context.startKoin

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin { androidContext(this@MyApplication) }

        loadKoinModules(viewModelModule)
    }
}