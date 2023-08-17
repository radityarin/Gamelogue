package com.radityarin.gamelogue.domain.usecases.games

import com.radityarin.gamelogue.data.source.remote.network.Status
import com.radityarin.gamelogue.domain.model.Game
import com.radityarin.gamelogue.domain.repositories.AppRepository
import kotlinx.coroutines.flow.Flow

class GamesInteractor(private val repository: AppRepository) : GamesUseCase {

    override fun getAllGames(): Flow<Status<List<Game>>> =
        repository.getAllGames()

    override fun getAllGamesWithPagination(page: Int, pageSize: Int): Flow<Status<List<Game>>> =
        repository.getAllGamesWithPagination(page, pageSize)

    override fun searchGames(query: String): Flow<Status<List<Game>>> =
        repository.searchGames(query)

    override fun getGamesDetail(id: Int): Flow<Status<Game>> =
        repository.getGamesDetail(id)

    override fun getFavoriteGames(): Flow<Status<List<Game>>> =
        repository.getFavoriteGames()

    override fun insertFavoriteGame(game: Game) =
        repository.insertFavoriteGame(game)

    override fun deleteFavoriteGame(game: Game) =
        repository.deleteFavoriteGame(game)
}