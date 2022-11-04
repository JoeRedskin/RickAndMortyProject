package com.example.rickandmortyproject.di.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.rickandmortyproject.presentation.viewmodel.CharacterDetailsViewModel
import com.example.rickandmortyproject.presentation.viewmodel.CharacterViewModel
import com.example.rickandmortyproject.presentation.viewmodel.EpisodeViewModel
import com.example.rickandmortyproject.presentation.viewmodel.LocationViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ViewModelFactoryModule {
    @Binds
    fun bindViewModelFactory(viewModelFactory: ViewModelFactory): ViewModelProvider.Factory
}