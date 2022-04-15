package com.example.mytranslator.app

import android.app.Application
import com.example.mytranslator.di.application
import com.example.mytranslator.di.mainScreen
import org.koin.core.context.startKoin

class InterpreterApp : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(listOf(application, mainScreen))
        }
    }
}