package com.example.rickandmortyproject.di.network

import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [NetworkModule::class])
interface NetworkComponent : NetworkProvider {

    companion object {
        fun init(): NetworkComponent {
            return DaggerNetworkComponent.factory().create()
        }
    }

    @Component.Factory
    interface Factory {
        fun create(): NetworkComponent
    }
}