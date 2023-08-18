package com.radityarin.gamelogue.data.source.remote

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.radityarin.gamelogue.data.source.remote.network.ApiResponse
import com.radityarin.gamelogue.data.source.remote.network.ApiServiceApp
import com.radityarin.gamelogue.domain.model.Game
import com.radityarin.gamelogue.utils.mapper.GameResponseToDomainMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow

const val NETWORK_PAGE_SIZE = 25

class RemoteDataSource(
    private val apiServiceApp: ApiServiceApp
) {

    suspend fun getAllGames(): Flow<ApiResponse<List<Game>>> {
        return channelFlow {
            try {
                val response = apiServiceApp.getAllGames()
                send(
                    ApiResponse.Success(
                        GameResponseToDomainMapper.apply(
                            response.gameResponses
                        )
                    )
                )
            } catch (e: java.lang.Exception) {
                send(ApiResponse.Error(e.message.toString()))
            }
        }
    }

    suspend fun getGamesPagination(): Flow<PagingData<Game>> {
        return Pager(
            config = PagingConfig(
                pageSize = NETWORK_PAGE_SIZE,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {
                GamePagingSource(service = apiServiceApp)
            }
        ).flow
    }

    suspend fun getAllGamesWithPagination(page: Int, pageSize: Int): Flow<ApiResponse<List<Game>>> {
        return channelFlow {
            try {
                val response =
                    apiServiceApp.getAllGamesWithPagination(page = page, pageSize = pageSize)
                send(
                    ApiResponse.Success(
                        GameResponseToDomainMapper.apply(
                            response.gameResponses
                        )
                    )
                )
            } catch (e: java.lang.Exception) {
                send(ApiResponse.Error(e.message.toString()))
            }
        }
    }

    suspend fun searchGames(query: String): Flow<ApiResponse<List<Game>>> {
        return channelFlow {
            try {
                val response = apiServiceApp.searchGames(query = query)
                send(
                    ApiResponse.Success(
                        GameResponseToDomainMapper.apply(
                            response.gameResponses
                        )
                    )
                )
            } catch (e: java.lang.Exception) {
                send(ApiResponse.Error(e.message.toString()))
            }
        }
    }

    suspend fun getGamesDetail(id: Int): Flow<ApiResponse<Game>> {
        return channelFlow {
            try {
                val response = apiServiceApp.getGamesDetail(id = id)
                send(
                    ApiResponse.Success(
                        GameResponseToDomainMapper.apply(
                            response
                        )
                    )
                )
            } catch (e: java.lang.Exception) {
                send(ApiResponse.Error(e.message.toString()))
            }
        }
    }

}