package com.example.rickandmortyproject.domain.use_case.location

import com.example.rickandmortyproject.data.model.Location
import com.example.rickandmortyproject.data.repository.LocationRepositoryImpl
import javax.inject.Inject

class GetLocationsByIdsUseCase @Inject constructor(private val repository: LocationRepositoryImpl) {

    suspend operator fun invoke(idList: List<Int>): List<Location>? {
        return repository.getLocationsByIds(idList)
    }
}