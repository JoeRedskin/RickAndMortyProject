package com.example.rickandmortyproject.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.paging.PagingData
import com.example.rickandmortyproject.data.model.Location
import com.example.rickandmortyproject.domain.use_case.location.GetLocationsByQueryUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocationViewModel @Inject constructor(private val getLocationsByQueryUseCase: GetLocationsByQueryUseCase) :
    ViewModel() {

    var queryName = ""
    var queryType = ""
    var queryDimension = ""

    fun searchLocations(): Flow<PagingData<Location>> =
        getLocationsByQueryUseCase(getQuery())

    fun setQuery(query: Location) {
        queryName = query.name
        queryType = query.type
        queryDimension = query.dimension
    }

    fun getQuery(): Location {
        return Location(name = queryName,
            type = queryType,
            dimension = queryDimension)
    }
}