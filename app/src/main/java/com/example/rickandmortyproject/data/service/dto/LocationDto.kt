package com.example.rickandmortyproject.data.service.dto

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class LocationDto(
    @SerializedName("id") val id: Int = 0,
    @SerializedName("name") val name: String = "",
    @SerializedName("type") val type: String = "",
    @SerializedName("dimension") val dimension: String = "",
    @SerializedName("residents") val residents: List<String> = emptyList(),
    @SerializedName("url") val locationUrl: String = "",
    @SerializedName("created") val created: String = "",
) : Parcelable, ModelDto()