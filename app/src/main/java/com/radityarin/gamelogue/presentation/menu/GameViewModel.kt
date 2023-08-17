package com.radityarin.gamelogue.presentation.menu

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.radityarin.gamelogue.data.source.local.pref.ProfilePrefs
import com.radityarin.gamelogue.data.source.remote.network.Status
import com.radityarin.gamelogue.domain.model.Game
import com.radityarin.gamelogue.domain.usecases.games.GamesUseCase
import kotlinx.coroutines.launch

class GameViewModel(
    private val gamesUseCase: GamesUseCase
) : ViewModel() {

    val showLoadingLiveData = MutableLiveData<Boolean>()

    private val _games = MutableLiveData<List<Game>>()
    val games: LiveData<List<Game>> = _games

    private val _game = MutableLiveData<Game>()
    val game: LiveData<Game> = _game

    private val _isGameFavorite = MutableLiveData<Boolean>()
    val isGameFavorite: LiveData<Boolean> = _isGameFavorite

    fun getAllGames() {
        viewModelScope.launch {
            gamesUseCase.getAllGames().collect { response ->
                when (response) {
                    is Status.Loading -> {
                        showLoadingLiveData.value = true
                    }
                    is Status.Success -> {
                        showLoadingLiveData.value = false
                        _games.value = response.data ?: emptyList()
                    }
                    is Status.Error -> {
                        showLoadingLiveData.value = false
                    }
                    else -> {}
                }
            }
        }
    }

    fun getFavoriteGames() {
        viewModelScope.launch {
            gamesUseCase.getFavoriteGames().collect { response ->
                when (response) {
                    is Status.Loading -> {
                        showLoadingLiveData.value = true
                    }
                    is Status.Success -> {
                        showLoadingLiveData.value = false
                        _games.value = response.data ?: emptyList()
                    }
                    is Status.Error -> {
                        showLoadingLiveData.value = false
                    }
                    else -> {}
                }
            }
        }
    }

    fun searchGames(query: String) {
        viewModelScope.launch {
            gamesUseCase.searchGames(query = query).collect { response ->
                when (response) {
                    is Status.Loading -> {
                        showLoadingLiveData.value = true
                    }
                    is Status.Success -> {
                        showLoadingLiveData.value = false
                        _games.value = response.data ?: emptyList()
                    }
                    is Status.Error -> {
                        showLoadingLiveData.value = false
                    }
                    else -> {}
                }
            }
        }
    }

    fun getGamesDetail(game: Game) {
        viewModelScope.launch {
            gamesUseCase.getGamesDetail(game.id).collect { response ->
                when (response) {
                    is Status.Loading -> {
                        showLoadingLiveData.value = true
                    }
                    is Status.Success -> {
                        showLoadingLiveData.value = false
                        val gamesDetail = response.data ?: Game()
                        _game.value = gamesDetail
                        checkFavorite(gamesDetail.id)
                    }
                    is Status.Error -> {
                        showLoadingLiveData.value = false
                    }
                    else -> {}
                }
            }
        }
    }

    private fun checkFavorite(id: Int) {
        viewModelScope.launch {
            gamesUseCase.getFavoriteGames().collect { response ->
                when (response) {
                    is Status.Loading -> {
                        showLoadingLiveData.value = true
                    }
                    is Status.Success -> {
                        showLoadingLiveData.value = false
                        val gamesDetail = response.data ?: emptyList()
                        _isGameFavorite.value = gamesDetail.any { it.id == id }
                    }
                    is Status.Error -> {
                        showLoadingLiveData.value = false
                    }
                    else -> {}
                }

            }
        }
    }

    private fun insertFavoriteGame(game: Game) {
        viewModelScope.launch {
            gamesUseCase.insertFavoriteGame(game)
        }
    }

    private fun deleteFavoriteGame(game: Game) {
        viewModelScope.launch {
            gamesUseCase.deleteFavoriteGame(game)
        }
    }

    fun updateFavoriteGame(game: Game) {
        val isFavorite = isGameFavorite.value ?: false
        _isGameFavorite.value = !isFavorite
        if (isFavorite) {
            deleteFavoriteGame(game)
        } else {
            insertFavoriteGame(game)
        }
    }
}