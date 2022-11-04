package com.example.rickandmortyproject.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.rickandmortyproject.data.model.Character

@Dao
interface CharacterDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(character: Character)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(characters: List<Character>)

    @Query("SELECT * FROM characters " +
            "WHERE name LIKE '%' || :name || '%' " +
            "AND status LIKE '%' || :status || '%' " +
            "AND gender LIKE :gender || '%' " +
            "AND species LIKE '%' || :species || '%' " +
            "AND type LIKE '%' || :type || '%' " +
            "LIMIT :limit OFFSET :offset")
    suspend fun getByQuery(
        limit: Int,
        offset: Int,
        name: String,
        status: String,
        gender: String,
        species: String,
        type: String,
    ): List<Character>

    @Query("SELECT * FROM characters WHERE id IN (:queryId)")
    suspend fun getById(queryId: Int): Character

    @Query("SELECT * FROM characters WHERE id IN (:queryId)")
    suspend fun getByIds(queryId: List<Int>): List<Character>

    @Query("DELETE FROM characters ")
    suspend fun deleteAll()
}

