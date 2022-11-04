package com.example.rickandmortyproject.data.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "episodes")
data class Episode(
    @PrimaryKey val id: Int = 0,
    val name: String = "",
    val airDate: String = "",
    val episodeCode: String = "",
    val characters: List<String> = emptyList(),
) : Parcelable, Model()
