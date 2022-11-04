package com.example.rickandmortyproject.data.repository

import android.util.Log
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.rickandmortyproject.data.db.AppDatabase
import com.example.rickandmortyproject.data.model.Location
import com.example.rickandmortyproject.data.paging.LocationPagingSource
import com.example.rickandmortyproject.data.service.RickAndMortyService
import com.example.rickandmortyproject.domain.mapper.LocationMapper
import com.example.rickandmortyproject.domain.repository.LocationRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocationRepositoryImpl @Inject constructor(
    private val service: RickAndMortyService,
    private val db: AppDatabase,
) : LocationRepository {

    override fun getLocationsByQuery(query: Location?): Flow<PagingData<Location>> {
        return Pager(
            config = PagingConfig(pageSize = LocationRepository.PAGE_SIZE,
                enablePlaceholders = false,
                initialLoadSize = LocationRepository.PAGE_SIZE),
            pagingSourceFactory = {
                LocationPagingSource(service,
                    db.locationDao(),
                    query)
            }
        ).flow
    }

    override suspend fun getLocationById(id: Int): Location? {
        return try {
            val response = LocationMapper().mapDtoToModel(service.getLocationById(id))
            db.locationDao().insert(response)
            db.locationDao().getById(id)
        } catch (t: Throwable) {
            Log.e(LocationRepository.TAG, t.message.toString())
            return try {
                db.locationDao().getById(id)
            } catch (t: Throwable) {
                Log.e(LocationRepository.TAG, t.message.toString())
                null
            }
        }
    }

    override suspend fun getLocationsByIds(idList: List<Int>): List<Location>? {
        return try {
            val response =
                service.getLocationsByIds(idList).map { LocationMapper().mapDtoToModel(it) }
            db.locationDao().insertAll(response)
            db.locationDao().getByIds(idList)
        } catch (t: Throwable) {
            Log.e(LocationRepository.TAG, t.message.toString())
            return try {
                db.locationDao().getByIds(idList)
            } catch (t: Throwable) {
                Log.e(LocationRepository.TAG, t.message.toString())
                null
            }
        }
    }
}