package com.example.rickandmortyproject.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.paging.PagingData
import com.example.rickandmortyproject.data.model.Character
import com.example.rickandmortyproject.domain.use_case.character.GetCharactersByQueryUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CharacterViewModel @Inject constructor(private val getCharactersByQueryUseCase: GetCharactersByQueryUseCase) :
    ViewModel() {

    var queryName = ""
    var queryStatus = ""
    var querySpecies = ""
    var queryType = ""
    var queryGender = ""

    fun searchCharacters(): Flow<PagingData<Character>> =
        getCharactersByQueryUseCase(getQuery())

    fun setQuery(query: Character) {
        queryName = query.name
        queryStatus = query.status
        querySpecies = query.species
        queryType = query.type
        queryGender = query.gender
    }

    fun getQuery(): Character {
        return Character(name = queryName,
            status = queryStatus,
            species = querySpecies,
            type = queryType,
            gender = queryGender)
    }
}