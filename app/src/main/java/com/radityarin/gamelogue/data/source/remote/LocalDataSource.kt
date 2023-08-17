package com.radityarin.gamelogue.data.source.remote

import com.radityarin.gamelogue.data.source.local.entity.store.GameEntity
import com.radityarin.gamelogue.data.source.local.room.AppDao
import kotlinx.coroutines.flow.Flow

class LocalDataSource(private val appDao: AppDao) {

    fun getFavoriteGames(): Flow<List<GameEntity>> = appDao.getFavoriteGames()

    suspend fun insertFavoriteGame(gameEntity: GameEntity) =
        appDao.insertFavoriteGame(gameEntity)

    suspend fun deleteFavoriteGame(id: Int) =
        appDao.deleteFavoriteGame(id)

}