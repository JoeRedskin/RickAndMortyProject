package com.example.rickandmortyproject.di.episode

import com.example.rickandmortyproject.di.DependenciesProvider
import com.example.rickandmortyproject.di.character.DaggerCharacterComponent
import com.example.rickandmortyproject.di.viewmodel.ViewModelFactoryModule
import com.example.rickandmortyproject.presentation.fragment.filter.EpisodeFilterFragment
import com.example.rickandmortyproject.presentation.fragment.list.EpisodeListFragment
import dagger.Component

@Component(
    dependencies = [DependenciesProvider::class],
    modules = [ViewModelFactoryModule::class, EpisodeModule::class]
)
interface EpisodeComponent {
    companion object {
        fun init(dependenciesProvider: DependenciesProvider): EpisodeComponent {
            return DaggerEpisodeComponent.factory().create(dependenciesProvider)
        }
    }

    @Component.Factory
    interface Factory {
        fun create(
            dependenciesProvider: DependenciesProvider,
        ): EpisodeComponent
    }

    fun inject(episodeListFragment: EpisodeListFragment)
    fun inject(episodeFilterFragment: EpisodeFilterFragment)
}