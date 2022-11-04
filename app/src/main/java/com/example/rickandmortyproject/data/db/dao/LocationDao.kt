package com.example.rickandmortyproject.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.rickandmortyproject.data.model.Location

@Dao
interface LocationDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(location: Location)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(locations: List<Location>)

    @Query("SELECT * FROM locations " +
            "WHERE name LIKE '%' || :name || '%' " +
            "AND type LIKE '%' || :type || '%' " +
            "AND dimension LIKE '%' || :dimension || '%' " +
            "LIMIT :limit OFFSET :offset")
    suspend fun getByQuery(
        limit: Int,
        offset: Int,
        name: String,
        type: String,
        dimension: String,
    ): List<Location>

    @Query("SELECT * FROM locations WHERE id IN (:queryId)")
    suspend fun getById(queryId: Int): Location

    @Query("SELECT * FROM locations WHERE id IN (:queryId)")
    suspend fun getByIds(queryId: List<Int>): List<Location>

    @Query("DELETE FROM locations")
    suspend fun clearAll()
}