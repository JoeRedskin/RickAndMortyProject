package com.example.rickandmortyproject.domain.use_case.location

import androidx.paging.PagingData
import com.example.rickandmortyproject.data.model.Location
import com.example.rickandmortyproject.data.repository.LocationRepositoryImpl
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetLocationsByQueryUseCase @Inject constructor(private val repository: LocationRepositoryImpl) {

    operator fun invoke(query: Location?): Flow<PagingData<Location>> {
        return repository.getLocationsByQuery(query)
    }
}

