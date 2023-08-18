package com.radityarin.gamelogue.data.source.remote

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.radityarin.gamelogue.data.source.remote.network.ApiServiceApp
import com.radityarin.gamelogue.domain.model.Game
import com.radityarin.gamelogue.utils.mapper.GameResponseToDomainMapper
import retrofit2.HttpException
import java.io.IOException

const val TMDB_STARTING_PAGE_INDEX = 1

class GamePagingSource(
    private val service: ApiServiceApp
) : PagingSource<Int, Game>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Game> {
        val pageIndex = params.key ?: TMDB_STARTING_PAGE_INDEX
        return try {
            val response = service.getAllGamesWithPagination(
                page = pageIndex,
                pageSize = pageIndex
            )
            val gameList = response.gameResponses
            val nextKey =
                if (gameList.isEmpty()) {
                    null
                } else {
                    // By default, initial load size = 3 * NETWORK PAGE SIZE
                    // ensure we're not requesting duplicating items at the 2nd request
                    pageIndex + (params.loadSize / NETWORK_PAGE_SIZE)
                }
            LoadResult.Page(
                data = GameResponseToDomainMapper.apply(gameList),
                prevKey = if (pageIndex == TMDB_STARTING_PAGE_INDEX) null else pageIndex,
                nextKey = nextKey
            )
        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        }
    }

    /**
     * The refresh key is used for subsequent calls to PagingSource.Load after the initial load.
     */
    override fun getRefreshKey(state: PagingState<Int, Game>): Int? {
        // We need to get the previous key (or next key if previous is null) of the page
        // that was closest to the most recently accessed index.
        // Anchor position is the most recently accessed index.
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }
}
