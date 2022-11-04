package com.example.rickandmortyproject.data.db.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.rickandmortyproject.data.model.Episode

@Dao
interface EpisodeDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(episode: Episode)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(episodes: List<Episode>)

    @Query("SELECT * FROM episodes " +
            "WHERE name LIKE '%' || :name || '%' " +
            "AND episodeCode LIKE '%' || :episodeCode || '%' " +
            "LIMIT :limit OFFSET :offset")
    suspend fun getByQuery(
        limit: Int,
        offset: Int,
        name: String,
        episodeCode: String,
    ): List<Episode>

    @Query("SELECT * FROM episodes WHERE id IN (:queryId) ")
    suspend fun getById(queryId: Int): Episode

    @Query("SELECT * FROM episodes WHERE id IN (:queryId) ")
    suspend fun getByIds(queryId: List<Int>): List<Episode>

    @Query("DELETE FROM episodes")
    suspend fun clearAll()
}