package com.example.rickandmortyproject.data.service

import com.example.rickandmortyproject.data.service.dto.CharacterDto
import com.example.rickandmortyproject.data.service.dto.EpisodeDto
import com.example.rickandmortyproject.data.service.dto.LocationDto
import com.example.rickandmortyproject.data.service.dto.PageResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RickAndMortyService {

    @GET("character/{character_id}")
    suspend fun getCharacterById(@Path("character_id") characterId: Int): CharacterDto

    @GET("character/{character_id}")
    suspend fun getCharactersByIds(@Path("character_id") characterId: List<Int>?): List<CharacterDto>

    @GET("location/{location_id}")
    suspend fun getLocationById(@Path("location_id") locationId: Int): LocationDto

    @GET("location/{location_id}")
    suspend fun getLocationsByIds(@Path("location_id") locationId: List<Int>?): List<LocationDto>

    @GET("episode/{episode_id}")
    suspend fun getEpisodeById(@Path("episode_id") episodeId: Int): EpisodeDto

    @GET("episode/{episode_id}")
    suspend fun getEpisodesByIds(@Path("episode_id") episodeId: List<Int>?): List<EpisodeDto>

    @GET("character")
    suspend fun getCharactersByAttributes(
        @Query("page") page: Int,
        @Query("name") name: String,
        @Query("status") status: String,
        @Query("species") species: String,
        @Query("type") type: String,
        @Query("gender") gender: String,
    ): PageResponse<CharacterDto>

    @GET("location")
    suspend fun getLocationByAttributes(
        @Query("page") page: Int,
        @Query("name") name: String,
        @Query("type") type: String,
        @Query("dimension") dimension: String,
    ): PageResponse<LocationDto>

    @GET("episode")
    suspend fun getEpisodesByAttributes(
        @Query("page") page: Int,
        @Query("name") name: String,
        @Query("episode_code") episodeCode: String,
    ): PageResponse<EpisodeDto>
}