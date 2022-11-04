package com.example.rickandmortyproject.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.rickandmortyproject.data.db.dao.CharacterDao
import com.example.rickandmortyproject.data.db.dao.EpisodeDao
import com.example.rickandmortyproject.data.db.dao.LocationDao
import com.example.rickandmortyproject.data.model.Character
import com.example.rickandmortyproject.data.model.Episode
import com.example.rickandmortyproject.data.model.Location

@Database(
    entities = [Character::class, Location::class, Episode::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun characterDao(): CharacterDao
    abstract fun locationDao(): LocationDao
    abstract fun episodeDao(): EpisodeDao
}