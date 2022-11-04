package com.example.rickandmortyproject.di

import android.content.Context
import com.example.rickandmortyproject.di.db.DatabaseComponent
import com.example.rickandmortyproject.di.db.DatabaseProvider
import com.example.rickandmortyproject.di.network.NetworkComponent
import com.example.rickandmortyproject.di.network.NetworkProvider
import dagger.BindsInstance
import dagger.Component

@Component(dependencies = [NetworkProvider::class, DatabaseProvider::class])
interface AppComponent : DependenciesProvider {

    companion object {
        fun init(context: Context): AppComponent {
            val networkProvider = NetworkComponent.init()
            val databaseProvider = DatabaseComponent.init(context)
            return DaggerAppComponent.factory().create(context, networkProvider, databaseProvider)
        }
    }

    @Component.Factory
    interface Factory {
        fun create(
            @BindsInstance context: Context,
            networkProvider: NetworkProvider,
            databaseProvider: DatabaseProvider,
        ): AppComponent
    }

}

