package com.example.rickandmortyproject.data.service.dto

import com.google.gson.annotations.SerializedName

data class InfoDto(
    @SerializedName("count") val count: Int = 0,
    @SerializedName("pages") val pages: Int = 0,
    @SerializedName("next") val nextPage: String? = "",
    @SerializedName("prev") val prevPage: String? = "",
)