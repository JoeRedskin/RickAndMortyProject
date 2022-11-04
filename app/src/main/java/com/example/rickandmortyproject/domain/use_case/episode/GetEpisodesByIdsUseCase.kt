package com.example.rickandmortyproject.domain.use_case.episode

import com.example.rickandmortyproject.data.model.Episode
import com.example.rickandmortyproject.data.repository.EpisodeRepositoryImpl
import javax.inject.Inject

class GetEpisodesByIdsUseCase @Inject constructor(private val repository: EpisodeRepositoryImpl) {

    suspend operator fun invoke(idList: List<Int>): List<Episode>? {
        return repository.getEpisodesByIds(idList)
    }
}