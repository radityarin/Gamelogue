package com.radityarin.gamelogue.data.source.local.room

import androidx.room.*
import com.radityarin.gamelogue.data.source.local.entity.store.GameEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface AppDao {

    @Query("SELECT * FROM game")
    fun getFavoriteGames(): Flow<List<GameEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavoriteGame(gameEntity: GameEntity)

    @Query("DELETE FROM game WHERE id=:id")
    suspend fun deleteFavoriteGame(id: Int)

}