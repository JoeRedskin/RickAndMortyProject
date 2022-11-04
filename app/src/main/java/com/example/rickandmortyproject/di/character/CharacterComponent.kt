package com.example.rickandmortyproject.di.character

import com.example.rickandmortyproject.di.DependenciesProvider
import com.example.rickandmortyproject.di.viewmodel.ViewModelFactoryModule
import com.example.rickandmortyproject.presentation.fragment.filter.CharacterFilterFragment
import com.example.rickandmortyproject.presentation.fragment.list.CharacterListFragment
import dagger.Component

@Component(
    dependencies = [DependenciesProvider::class],
    modules = [ViewModelFactoryModule::class, CharacterModule::class]
)
interface CharacterComponent {
    companion object {
        fun init(dependenciesProvider: DependenciesProvider): CharacterComponent {
            return DaggerCharacterComponent.factory().create(dependenciesProvider)
        }
    }

    @Component.Factory
    interface Factory {
        fun create(
            dependenciesProvider: DependenciesProvider,
        ): CharacterComponent
    }

    fun inject(characterListFragment: CharacterListFragment)
    fun inject(characterFilterFragment: CharacterFilterFragment)
}


