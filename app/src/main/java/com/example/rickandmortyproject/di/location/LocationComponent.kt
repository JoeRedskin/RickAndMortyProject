package com.example.rickandmortyproject.di.location

import com.example.rickandmortyproject.di.DependenciesProvider
import com.example.rickandmortyproject.di.viewmodel.ViewModelFactoryModule
import com.example.rickandmortyproject.presentation.fragment.filter.LocationFilterFragment
import com.example.rickandmortyproject.presentation.fragment.list.LocationListFragment
import dagger.Component

@Component(
    dependencies = [DependenciesProvider::class],
    modules = [ViewModelFactoryModule::class, LocationModule::class]
)
interface LocationComponent {
    companion object {
        fun init(dependenciesProvider: DependenciesProvider): LocationComponent {
            return DaggerLocationComponent.factory().create(dependenciesProvider)
        }
    }

    @Component.Factory
    interface Factory {
        fun create(
            dependenciesProvider: DependenciesProvider,
        ): LocationComponent
    }

    fun inject(locationListFragment: LocationListFragment)
    fun inject(locationFilterFragment: LocationFilterFragment)
}