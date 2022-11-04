package com.example.rickandmortyproject.di.episode

import androidx.lifecycle.ViewModel
import com.example.rickandmortyproject.di.viewmodel.ViewModelKey
import com.example.rickandmortyproject.presentation.viewmodel.EpisodeDetailsViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface EpisodeDetailsModule {

    @Binds
    @IntoMap
    @ViewModelKey(EpisodeDetailsViewModel::class)
    fun bindEpisodeDetailsViewModel(viewModel: EpisodeDetailsViewModel): ViewModel
}