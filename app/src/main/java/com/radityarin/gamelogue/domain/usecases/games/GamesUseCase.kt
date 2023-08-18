package com.radityarin.gamelogue.domain.usecases.games

import androidx.paging.PagingData
import com.radityarin.gamelogue.data.source.remote.network.Status
import com.radityarin.gamelogue.domain.model.Game
import kotlinx.coroutines.flow.Flow

interface GamesUseCase {
    fun getAllGames(): Flow<Status<List<Game>>>
    fun getAllGamesWithPagination(page: Int, pageSize: Int): Flow<Status<List<Game>>>
    fun searchGames(query: String): Flow<Status<List<Game>>>
    fun getGamesDetail(id: Int): Flow<Status<Game>>
    fun getFavoriteGames(): Flow<Status<List<Game>>>
    fun insertFavoriteGame(game: Game)
    fun deleteFavoriteGame(game: Game)
    fun getGamesPagination(): Flow<PagingData<Game>>
}