package com.radityarin.gamelogue.data.source

import androidx.paging.PagingData
import com.radityarin.gamelogue.data.source.remote.LocalDataSource
import com.radityarin.gamelogue.data.source.remote.RemoteDataSource
import com.radityarin.gamelogue.data.source.remote.network.ApiResponse
import com.radityarin.gamelogue.data.source.remote.network.Status
import com.radityarin.gamelogue.domain.model.Game
import com.radityarin.gamelogue.domain.repositories.AppRepository
import com.radityarin.gamelogue.utils.mapper.GameDomainToEntityMapper
import com.radityarin.gamelogue.utils.mapper.GameEntityToDomainMapper
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class AppRepositoryImpl(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val job: CoroutineScope
) : AppRepository {

    override fun getAllGames(): Flow<Status<List<Game>>> = channelFlow {
        send(Status.Loading())
        when (val apiResponse =
            remoteDataSource.getAllGames().first()) {
            is ApiResponse.Success -> {
                send(Status.Success(apiResponse.data))
            }
            is ApiResponse.Error -> {
                send(
                    Status.Error(
                        message = apiResponse.errorMessage,
                        errorCode = apiResponse.errorCode,
                        messageId = apiResponse.errorMessageID,
                        errorData = apiResponse.errorData
                    )
                )
            }
            is ApiResponse.HttpError -> {
                send(Status.HttpError(apiResponse.httpError))
            }
        }
    }

    override fun getAllGamesWithPagination(page: Int, pageSize: Int): Flow<Status<List<Game>>> =
        channelFlow {
            send(Status.Loading())
            when (val apiResponse =
                remoteDataSource.getAllGamesWithPagination(page = page, pageSize = pageSize)
                    .first()) {
                is ApiResponse.Success -> {
                    send(Status.Success(apiResponse.data))
                }
                is ApiResponse.Error -> {
                    send(
                        Status.Error(
                            message = apiResponse.errorMessage,
                            errorCode = apiResponse.errorCode,
                            messageId = apiResponse.errorMessageID,
                            errorData = apiResponse.errorData
                        )
                    )
                }
                is ApiResponse.HttpError -> {
                    send(Status.HttpError(apiResponse.httpError))
                }
            }
        }

    override fun getGamesPagination(): Flow<PagingData<Game>> =
        channelFlow {
            send(remoteDataSource.getGamesPagination().first())
        }

    override fun searchGames(query: String): Flow<Status<List<Game>>> = channelFlow {
        send(Status.Loading())
        when (val apiResponse =
            remoteDataSource.searchGames(query = query).first()) {
            is ApiResponse.Success -> {
                send(Status.Success(apiResponse.data))
            }
            is ApiResponse.Error -> {
                send(
                    Status.Error(
                        message = apiResponse.errorMessage,
                        errorCode = apiResponse.errorCode,
                        messageId = apiResponse.errorMessageID,
                        errorData = apiResponse.errorData
                    )
                )
            }
            is ApiResponse.HttpError -> {
                send(Status.HttpError(apiResponse.httpError))
            }
        }
    }

    override fun getGamesDetail(id: Int): Flow<Status<Game>> = channelFlow {
        send(Status.Loading())
        when (val apiResponse =
            remoteDataSource.getGamesDetail(id = id).first()) {
            is ApiResponse.Success -> {
                send(Status.Success(apiResponse.data))
            }
            is ApiResponse.Error -> {
                send(
                    Status.Error(
                        message = apiResponse.errorMessage,
                        errorCode = apiResponse.errorCode,
                        messageId = apiResponse.errorMessageID,
                        errorData = apiResponse.errorData
                    )
                )
            }
            is ApiResponse.HttpError -> {
                send(Status.HttpError(apiResponse.httpError))
            }
        }
    }

    override fun getFavoriteGames(): Flow<Status<List<Game>>> = channelFlow {
        send(Status.Loading())
        send(
            Status.Success(
                localDataSource.getFavoriteGames().map {
                    GameEntityToDomainMapper.apply(it)
                }.first()
            )
        )
    }

    override fun insertFavoriteGame(game: Game) {
        job.launch {
            val gameEntity = GameDomainToEntityMapper.apply(game)
            localDataSource.insertFavoriteGame(gameEntity)
        }
    }

    override fun deleteFavoriteGame(game: Game) {
        job.launch {
            val gameEntity = GameDomainToEntityMapper.apply(game)
            localDataSource.deleteFavoriteGame(gameEntity.id)
        }
    }

}