package com.example.proftranslatorfixgit.app

import android.app.Application
import com.example.proftranslatorfixgit.di.application
import com.example.proftranslatorfixgit.di.historyScreen
import com.example.proftranslatorfixgit.di.mainScreen
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class InterpreterApp : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(applicationContext)
            modules(listOf(application, mainScreen, historyScreen))
        }
    }
}