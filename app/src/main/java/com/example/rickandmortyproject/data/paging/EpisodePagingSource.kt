package com.example.rickandmortyproject.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.rickandmortyproject.data.db.dao.EpisodeDao
import com.example.rickandmortyproject.data.model.Episode
import com.example.rickandmortyproject.data.service.RickAndMortyService
import com.example.rickandmortyproject.data.service.dto.EpisodeDto
import com.example.rickandmortyproject.data.service.dto.PageResponse
import com.example.rickandmortyproject.domain.mapper.EpisodeMapper


class EpisodePagingSource(
    private val service: RickAndMortyService,
    private val dao: EpisodeDao,
    private val query: Episode?,
) :
    PagingSource<Int, Episode>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Episode> {
        val pageNumber = params.key ?: 1

        return try {
            val dbResult = getDataFromDB(params, pageNumber)
            return if (dbResult.isEmpty() || dbResult.size < 20) {
                val response = getDataFromNetwork(pageNumber)
                val items = response.results.map { EpisodeMapper().mapDtoToModel(it) }
                dao.insertAll(items)
                LoadResult.Page(
                    data = items,
                    prevKey = null,
                    nextKey = if (response.info.nextPage == null) null else pageNumber + 1
                )
            } else
                LoadResult.Page(
                    data = dbResult,
                    prevKey = null,
                    nextKey = pageNumber + 1
                )

        } catch (e: Exception) {
            e.printStackTrace()
            LoadResult.Error(e.fillInStackTrace())
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Episode>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    private suspend fun getDataFromDB(
        params: LoadParams<Int>,
        nextPageNumber: Int,
    ): List<Episode> {
        return dao.getByQuery(params.loadSize,
            (nextPageNumber - 1) * params.loadSize,
            name = query?.name ?: "",
            episodeCode = query?.episodeCode ?: "")
    }

    private suspend fun getDataFromNetwork(nextPageNumber: Int): PageResponse<EpisodeDto> {
        return service.getEpisodesByAttributes(nextPageNumber,
            name = query?.name ?: "",
            episodeCode = query?.episodeCode ?: "")
    }

}