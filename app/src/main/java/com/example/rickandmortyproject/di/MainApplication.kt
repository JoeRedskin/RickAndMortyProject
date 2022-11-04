package com.example.rickandmortyproject.di

import android.app.Application

class MainApplication : Application(), App {
    private var appComponent: AppComponent? = null

    override fun onCreate() {
        super.onCreate()
        appComponent = AppComponent.init(this)
    }

    private fun getAppComponent(): AppComponent {
        if (appComponent == null) {
            appComponent = AppComponent.init(applicationContext)
        }
        return appComponent!!
    }

    override fun getDependenciesProvider(): DependenciesProvider {
        return getAppComponent()
    }
}