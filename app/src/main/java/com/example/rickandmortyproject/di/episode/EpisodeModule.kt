package com.example.rickandmortyproject.di.episode

import androidx.lifecycle.ViewModel
import com.example.rickandmortyproject.di.viewmodel.ViewModelKey
import com.example.rickandmortyproject.presentation.viewmodel.EpisodeViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface EpisodeModule {
    @Binds
    @IntoMap
    @ViewModelKey(EpisodeViewModel::class)
    fun bindEpisodeViewModel(viewModel: EpisodeViewModel): ViewModel
}