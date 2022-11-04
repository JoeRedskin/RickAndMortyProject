package com.example.rickandmortyproject.di.location

import androidx.lifecycle.ViewModel
import com.example.rickandmortyproject.di.viewmodel.ViewModelKey
import com.example.rickandmortyproject.presentation.viewmodel.LocationDetailsViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface LocationDetailsModule {

    @Binds
    @IntoMap
    @ViewModelKey(LocationDetailsViewModel::class)
    fun bindLocationDetailsModule(viewModel: LocationDetailsViewModel): ViewModel
}