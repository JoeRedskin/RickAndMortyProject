package com.example.rickandmortyproject.di.character

import com.example.rickandmortyproject.data.model.Character
import com.example.rickandmortyproject.di.DependenciesProvider
import com.example.rickandmortyproject.di.viewmodel.ViewModelFactoryModule
import com.example.rickandmortyproject.presentation.fragment.details.CharacterDetailsFragment
import dagger.BindsInstance
import dagger.Component

@Component(
    dependencies = [DependenciesProvider::class],
    modules = [ViewModelFactoryModule::class, CharacterDetailsModule::class]
)
interface CharacterDetailsComponent {
    companion object {
        fun init(
            id: Int,
            dependenciesProvider: DependenciesProvider,
        ): CharacterDetailsComponent {
            return DaggerCharacterDetailsComponent.factory().create(id, dependenciesProvider)
        }
    }

    @Component.Factory
    interface Factory {
        fun create(
            @BindsInstance id: Int,
            dependenciesProvider: DependenciesProvider,
        ): CharacterDetailsComponent
    }

    fun inject(characterDetailsFragment: CharacterDetailsFragment)
}