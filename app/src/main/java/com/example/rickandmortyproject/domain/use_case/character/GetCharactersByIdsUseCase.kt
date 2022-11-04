package com.example.rickandmortyproject.domain.use_case.character

import com.example.rickandmortyproject.data.model.Character
import com.example.rickandmortyproject.data.repository.CharacterRepositoryImpl
import javax.inject.Inject

class GetCharactersByIdsUseCase @Inject constructor(private val repository: CharacterRepositoryImpl) {

    suspend operator fun invoke (idList: List<Int>): List<Character>? {
        return repository.getCharactersByIds(idList)
    }
}