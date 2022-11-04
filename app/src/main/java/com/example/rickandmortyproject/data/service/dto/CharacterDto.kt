package com.example.rickandmortyproject.data.service.dto

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class CharacterDto(
    @SerializedName("id") val id: Int = 0,
    @SerializedName("name") val name: String = "",
    @SerializedName("status") val status: String = "",
    @SerializedName("species") val species: String = "",
    @SerializedName("type") val type: String = "",
    @SerializedName("gender") val gender: String = "",
    @SerializedName("origin") val originLocation: LocationDto = LocationDto(),
    @SerializedName("location") val lastLocation: LocationDto = LocationDto(),
    @SerializedName("image") val imageUrl: String = "",
    @SerializedName("episode") val episodes: List<String> = emptyList(),
    @SerializedName("url") val url: String = "",
    @SerializedName("created") val created: String = "",
) : Parcelable, ModelDto()