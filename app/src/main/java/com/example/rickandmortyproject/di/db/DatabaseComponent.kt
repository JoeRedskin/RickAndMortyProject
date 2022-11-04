package com.example.rickandmortyproject.di.db

import android.content.Context
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [DatabaseModule::class])
interface DatabaseComponent : DatabaseProvider {

    companion object {
        fun init(context: Context): DatabaseComponent {
            return DaggerDatabaseComponent.factory().create(context)
        }
    }

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): DatabaseComponent
    }
}