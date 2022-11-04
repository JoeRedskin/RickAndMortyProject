package com.example.rickandmortyproject.domain.use_case.episode

import com.example.rickandmortyproject.data.model.Episode
import com.example.rickandmortyproject.data.repository.EpisodeRepositoryImpl
import javax.inject.Inject

class GetEpisodeByIdUseCase @Inject constructor(private val repository: EpisodeRepositoryImpl) {

    suspend operator fun invoke(id:Int): Episode? {
        return repository.getEpisodeById(id)
    }
}