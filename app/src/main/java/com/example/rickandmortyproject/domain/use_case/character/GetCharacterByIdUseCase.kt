package com.example.rickandmortyproject.domain.use_case.character

import com.example.rickandmortyproject.data.model.Character
import com.example.rickandmortyproject.data.repository.CharacterRepositoryImpl
import javax.inject.Inject

class GetCharacterByIdUseCase @Inject constructor(private val repository: CharacterRepositoryImpl) {

    suspend operator fun invoke(id:Int): Character? {
        return repository.getCharacterById(id)
    }
}