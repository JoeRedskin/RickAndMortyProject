package com.example.rickandmortyproject.domain.repository

import androidx.paging.PagingData
import com.example.rickandmortyproject.data.model.Episode
import kotlinx.coroutines.flow.Flow

interface EpisodeRepository {
    fun getEpisodesByQuery(query: Episode?): Flow<PagingData<Episode>>
    suspend fun getEpisodeById(id: Int): Episode?
    suspend fun getEpisodesByIds(idList: List<Int>): List<Episode>?

    companion object {
        const val TAG = "EpisodeRepository"
        const val PAGE_SIZE = 20
    }
}