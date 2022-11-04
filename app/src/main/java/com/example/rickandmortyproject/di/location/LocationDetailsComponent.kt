package com.example.rickandmortyproject.di.location

import com.example.rickandmortyproject.data.model.Location
import com.example.rickandmortyproject.di.DependenciesProvider
import com.example.rickandmortyproject.di.viewmodel.ViewModelFactoryModule
import com.example.rickandmortyproject.presentation.fragment.details.LocationDetailsFragment
import dagger.BindsInstance
import dagger.Component

@Component(
    dependencies = [DependenciesProvider::class],
    modules = [ViewModelFactoryModule::class, LocationDetailsModule::class]
)
interface LocationDetailsComponent {
    companion object {
        fun init(
            id: Int,
            dependenciesProvider: DependenciesProvider,
        ): LocationDetailsComponent {
            return DaggerLocationDetailsComponent.factory().create(id, dependenciesProvider)
        }
    }

    @Component.Factory
    interface Factory {
        fun create(
            @BindsInstance id: Int,
            dependenciesProvider: DependenciesProvider,
        ): LocationDetailsComponent
    }

    fun inject(locationDetailsFragment: LocationDetailsFragment)
}