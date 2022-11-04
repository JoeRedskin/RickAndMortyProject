package com.example.rickandmortyproject.di.character

import androidx.lifecycle.ViewModel
import com.example.rickandmortyproject.di.viewmodel.ViewModelKey
import com.example.rickandmortyproject.presentation.viewmodel.CharacterDetailsViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface CharacterDetailsModule {

    @Binds
    @IntoMap
    @ViewModelKey(CharacterDetailsViewModel::class)
    fun bindCharacterDetailsViewModel(viewModel: CharacterDetailsViewModel): ViewModel
}