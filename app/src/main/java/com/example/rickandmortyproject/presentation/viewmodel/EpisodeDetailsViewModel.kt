package com.example.rickandmortyproject.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickandmortyproject.data.model.Character
import com.example.rickandmortyproject.data.model.Episode
import com.example.rickandmortyproject.domain.use_case.character.GetCharactersByIdsUseCase
import com.example.rickandmortyproject.domain.use_case.episode.GetEpisodeByIdUseCase
import com.example.rickandmortyproject.utils.getIdList
import kotlinx.coroutines.launch
import javax.inject.Inject

class EpisodeDetailsViewModel @Inject constructor(
    private val id: Int,
    private val getEpisodeByIdUseCase: GetEpisodeByIdUseCase,
    private val getCharactersByIdsUseCase: GetCharactersByIdsUseCase,
) : ViewModel() {

    private val _episode: MutableLiveData<Episode> = MutableLiveData<Episode>()
    private val _characters: MutableLiveData<List<Character>> = MutableLiveData<List<Character>>()
    val episode: LiveData<Episode> get() = _episode
    val characters: LiveData<List<Character>> get() = _characters

    init {
        viewModelScope.launch { getEpisode() }
    }

    private suspend fun getEpisode() {
        try {
            getEpisodeByIdUseCase(id).also {
                if (it != null) {
                    _episode.value = it
                    getCharacters()
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private suspend fun getCharacters() {
        try {
            val idList = getIdList(_episode.value?.characters)
            getCharactersByIdsUseCase(idList).also {
                if (it != null) _characters.value = it
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}