package com.example.rickandmortyproject.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.paging.PagingData
import com.example.rickandmortyproject.data.model.Episode
import com.example.rickandmortyproject.domain.use_case.episode.GetEpisodesByQueryUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class EpisodeViewModel @Inject constructor(private val getEpisodesByQueryUseCase: GetEpisodesByQueryUseCase) :
    ViewModel() {

    var queryName = ""
    var queryCode = ""

    fun searchEpisodes(): Flow<PagingData<Episode>> =
        getEpisodesByQueryUseCase(getQuery())

    fun setQuery(query: Episode) {
        queryName = query.name
        queryCode = query.episodeCode
    }

    fun getQuery(): Episode {
        return Episode(name = queryName,
            episodeCode = queryCode)
    }
}