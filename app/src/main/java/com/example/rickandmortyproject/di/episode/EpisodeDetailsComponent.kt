package com.example.rickandmortyproject.di.episode

import com.example.rickandmortyproject.di.DependenciesProvider
import com.example.rickandmortyproject.di.viewmodel.ViewModelFactoryModule
import com.example.rickandmortyproject.presentation.fragment.details.EpisodeDetailsFragment
import dagger.BindsInstance
import dagger.Component

@Component(
    dependencies = [DependenciesProvider::class],
    modules = [ViewModelFactoryModule::class, EpisodeDetailsModule::class]
)
interface EpisodeDetailsComponent {
    companion object {
        fun init(
            id: Int,
            dependenciesProvider: DependenciesProvider,
        ): EpisodeDetailsComponent {
            return DaggerEpisodeDetailsComponent.factory().create(id, dependenciesProvider)
        }
    }

    @Component.Factory
    interface Factory {
        fun create(
            @BindsInstance id: Int,
            dependenciesProvider: DependenciesProvider,
        ): EpisodeDetailsComponent
    }

    fun inject(episodeDetailsFragment: EpisodeDetailsFragment)
}