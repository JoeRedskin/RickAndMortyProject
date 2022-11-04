package com.example.rickandmortyproject.di.location

import androidx.lifecycle.ViewModel
import com.example.rickandmortyproject.di.viewmodel.ViewModelKey
import com.example.rickandmortyproject.presentation.viewmodel.LocationViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface LocationModule {

    @Binds
    @IntoMap
    @ViewModelKey(LocationViewModel::class)
    fun bindLocationViewModel(viewModel: LocationViewModel): ViewModel
}