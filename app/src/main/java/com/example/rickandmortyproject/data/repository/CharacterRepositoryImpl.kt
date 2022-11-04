package com.example.rickandmortyproject.data.repository

import android.util.Log
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.rickandmortyproject.data.db.AppDatabase
import com.example.rickandmortyproject.data.model.Character
import com.example.rickandmortyproject.data.paging.CharacterPagingSource
import com.example.rickandmortyproject.data.service.RickAndMortyService
import com.example.rickandmortyproject.domain.mapper.CharacterMapper
import com.example.rickandmortyproject.domain.repository.CharacterRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CharacterRepositoryImpl @Inject constructor(
    private val service: RickAndMortyService,
    private val db: AppDatabase,
) : CharacterRepository {

    override fun getCharactersByQuery(query: Character?): Flow<PagingData<Character>> {
        return Pager(
            config = PagingConfig(pageSize = CharacterRepository.PAGE_SIZE,
                enablePlaceholders = false,
                initialLoadSize = CharacterRepository.PAGE_SIZE),
            pagingSourceFactory = {
                CharacterPagingSource(service,
                    db.characterDao(),
                    query)
            }
        ).flow
    }

    override suspend fun getCharacterById(id: Int): Character? {
        return try {
            val response = service.getCharacterById(id)
            val character = CharacterMapper().mapDtoToModel(response)
            db.characterDao().insert(character)
            db.characterDao().getById(id)
        } catch (t: Throwable) {
            Log.e(CharacterRepository.TAG, t.message.toString())
            return try {
                db.characterDao().getById(id)
            } catch (t: Throwable) {
                Log.e(CharacterRepository.TAG, t.message.toString())
                null
            }
        }
    }

    override suspend fun getCharactersByIds(idList: List<Int>): List<Character>? {
        return try {
            val response =
                service.getCharactersByIds(idList).map { CharacterMapper().mapDtoToModel(it) }
            db.characterDao().insertAll(response)
            db.characterDao().getByIds(idList)
        } catch (t: Throwable) {
            Log.e(CharacterRepository.TAG, t.message.toString())
            return try {
                db.characterDao().getByIds(idList)
            } catch (t: Throwable) {
                Log.e(CharacterRepository.TAG, t.message.toString())
                null
            }
        }
    }
}