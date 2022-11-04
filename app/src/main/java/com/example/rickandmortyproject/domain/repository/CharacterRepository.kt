package com.example.rickandmortyproject.domain.repository

import androidx.paging.PagingData
import com.example.rickandmortyproject.data.model.Character
import kotlinx.coroutines.flow.Flow

interface CharacterRepository {
    fun getCharactersByQuery(query: Character?): Flow<PagingData<Character>>
    suspend fun getCharacterById(id: Int): Character?
    suspend fun getCharactersByIds(idList: List<Int>): List<Character>?

    companion object{
        const val TAG = "CharacterRepository"
        const val PAGE_SIZE = 20
    }
}