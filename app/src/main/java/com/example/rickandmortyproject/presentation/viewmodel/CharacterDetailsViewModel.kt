package com.example.rickandmortyproject.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickandmortyproject.data.model.Character
import com.example.rickandmortyproject.data.model.Episode
import com.example.rickandmortyproject.domain.use_case.character.GetCharacterByIdUseCase
import com.example.rickandmortyproject.domain.use_case.episode.GetEpisodesByIdsUseCase
import com.example.rickandmortyproject.utils.getIdList
import kotlinx.coroutines.launch
import javax.inject.Inject

class CharacterDetailsViewModel @Inject constructor(
    private val id: Int,
    private val getCharacterByIdUseCase: GetCharacterByIdUseCase,
    private val getEpisodesByIdsUseCase: GetEpisodesByIdsUseCase,
) : ViewModel() {

    private val _character: MutableLiveData<Character> = MutableLiveData<Character>()
    private val _episodes: MutableLiveData<List<Episode>> = MutableLiveData<List<Episode>>()
    val character: LiveData<Character> get() = _character
    val episodes: LiveData<List<Episode>> get() = _episodes

    init {
        viewModelScope.launch { getCharacter() }
    }

    private suspend fun getCharacter() {
        try {
            getCharacterByIdUseCase(id).also {
                if (it != null) {
                    _character.value = it
                    getEpisodes()
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private suspend fun getEpisodes() {
        try {
            val idList = getIdList(_character.value?.episodes)
            getEpisodesByIdsUseCase(idList).also {
                if (it != null) _episodes.value = it
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}