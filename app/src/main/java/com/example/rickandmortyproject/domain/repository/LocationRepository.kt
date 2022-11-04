package com.example.rickandmortyproject.domain.repository

import androidx.paging.PagingData
import com.example.rickandmortyproject.data.model.Character
import com.example.rickandmortyproject.data.model.Location
import kotlinx.coroutines.flow.Flow

interface LocationRepository {
    fun getLocationsByQuery(query: Location?): Flow<PagingData<Location>>
    suspend fun getLocationById(id: Int): Location?
    suspend fun getLocationsByIds(idList: List<Int>): List<Location>?

    companion object {
        const val TAG = "LocationRepository"
        const val PAGE_SIZE = 20
    }
}