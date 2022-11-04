package com.example.rickandmortyproject.domain.use_case.episode

import androidx.paging.PagingData
import com.example.rickandmortyproject.data.model.Episode
import com.example.rickandmortyproject.data.repository.EpisodeRepositoryImpl
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetEpisodesByQueryUseCase @Inject constructor(private val repository: EpisodeRepositoryImpl) {

     operator fun invoke(query: Episode?): Flow<PagingData<Episode>> {
        return repository.getEpisodesByQuery(query)
    }
}