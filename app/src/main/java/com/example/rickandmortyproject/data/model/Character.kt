package com.example.rickandmortyproject.data.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "characters")
data class Character(
    @PrimaryKey val id: Int = 0,
    val name: String = "",
    val status: String = "",
    val species: String = "",
    val type: String = "",
    val gender: String = "",
    val originLocationId: Int = 0,
    val originLocationName: String = "",
    val lastLocationId: Int = 0,
    val lastLocationName: String = "",
    val imageUrl: String = "",
    val episodes: List<String> = emptyList(),
) : Parcelable, Model()

