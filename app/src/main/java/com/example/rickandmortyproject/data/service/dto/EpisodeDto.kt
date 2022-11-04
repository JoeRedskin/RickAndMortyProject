package com.example.rickandmortyproject.data.service.dto

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class EpisodeDto(
    @SerializedName("id") val id: Int = 0,
    @SerializedName("name") val name: String = "",
    @SerializedName("air_date") val airDate: String = "",
    @SerializedName("episode") val episodeCode: String = "",
    @SerializedName("characters") val characters: List<String> = emptyList(),
    @SerializedName("url") val episodeUrl: String = "",
    @SerializedName("created") val created: String = "",
) : Parcelable, ModelDto()