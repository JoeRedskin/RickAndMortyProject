package com.example.rickandmortyproject.data.repository

import android.util.Log
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.rickandmortyproject.data.db.AppDatabase
import com.example.rickandmortyproject.data.model.Episode
import com.example.rickandmortyproject.data.paging.EpisodePagingSource
import com.example.rickandmortyproject.data.service.RickAndMortyService
import com.example.rickandmortyproject.domain.mapper.EpisodeMapper
import com.example.rickandmortyproject.domain.repository.EpisodeRepository
import com.example.rickandmortyproject.domain.repository.LocationRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class EpisodeRepositoryImpl @Inject constructor(
    private val service: RickAndMortyService,
    private val db: AppDatabase,
) : EpisodeRepository {

    override fun getEpisodesByQuery(query: Episode?): Flow<PagingData<Episode>> {
        return Pager(
            config = PagingConfig(pageSize = EpisodeRepository.PAGE_SIZE,
                enablePlaceholders = false,
                initialLoadSize = LocationRepository.PAGE_SIZE),
            pagingSourceFactory = { EpisodePagingSource(service, db.episodeDao(), query) }
        ).flow
    }

    override suspend fun getEpisodeById(id: Int): Episode? {
        return try {
            val response = EpisodeMapper().mapDtoToModel(service.getEpisodeById(id))
            db.episodeDao().insert(response)
            db.episodeDao().getById(id)
        } catch (t: Throwable) {
            Log.e(LocationRepository.TAG, t.message.toString())
            return try {
                db.episodeDao().getById(id)
            } catch (t: Throwable) {
                Log.e(LocationRepository.TAG, t.message.toString())
                null
            }
        }
    }

    override suspend fun getEpisodesByIds(idList: List<Int>): List<Episode>? {
        return try {
            val response =
                service.getEpisodesByIds(idList).map { EpisodeMapper().mapDtoToModel(it) }
            db.episodeDao().insertAll(response)
            db.episodeDao().getByIds(idList)
        } catch (t: Throwable) {
            Log.e(LocationRepository.TAG, t.message.toString())
            return try {
                db.episodeDao().getByIds(idList)
            } catch (t: Throwable) {
                Log.e(LocationRepository.TAG, t.message.toString())
                null
            }
        }
    }
}