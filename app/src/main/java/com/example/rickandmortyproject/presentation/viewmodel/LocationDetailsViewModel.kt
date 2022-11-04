package com.example.rickandmortyproject.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickandmortyproject.data.model.Character
import com.example.rickandmortyproject.data.model.Location
import com.example.rickandmortyproject.domain.use_case.character.GetCharactersByIdsUseCase
import com.example.rickandmortyproject.domain.use_case.location.GetLocationByIdUseCase
import com.example.rickandmortyproject.utils.getIdList
import kotlinx.coroutines.launch
import javax.inject.Inject

class LocationDetailsViewModel @Inject constructor(
    private val id: Int,
    private val getLocationByIdUseCase: GetLocationByIdUseCase,
    private val getCharactersByIdsUseCase: GetCharactersByIdsUseCase,
) : ViewModel() {

    private val _location: MutableLiveData<Location> = MutableLiveData<Location>()
    private val _characters: MutableLiveData<List<Character>> = MutableLiveData<List<Character>>()
    val location: LiveData<Location> get() = _location
    val characters: LiveData<List<Character>> get() = _characters

    init {
        viewModelScope.launch { getLocation() }
    }

    private suspend fun getLocation() {
        try {
            getLocationByIdUseCase(id).also {
                if (it != null) {
                    _location.value = it
                    getCharacters()
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private suspend fun getCharacters() {
        try {
            val idList = getIdList(_location.value?.residents)
            getCharactersByIdsUseCase(idList).also {
                if (it != null) _characters.value = it
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}