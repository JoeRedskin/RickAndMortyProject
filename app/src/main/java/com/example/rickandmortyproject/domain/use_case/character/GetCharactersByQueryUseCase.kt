package com.example.rickandmortyproject.domain.use_case.character

import androidx.paging.PagingData
import com.example.rickandmortyproject.data.model.Character
import com.example.rickandmortyproject.data.repository.CharacterRepositoryImpl
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCharactersByQueryUseCase @Inject constructor(private val repository: CharacterRepositoryImpl) {

    operator fun invoke(query: Character): Flow<PagingData<Character>> {
        return repository.getCharactersByQuery(query)
    }
}

