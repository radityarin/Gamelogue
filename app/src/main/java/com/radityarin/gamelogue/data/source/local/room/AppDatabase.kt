package com.radityarin.gamelogue.data.source.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.radityarin.gamelogue.data.source.local.entity.store.GameEntity

@Database(
    entities = [GameEntity::class],
    version = 1,
    exportSchema = false
)

@TypeConverters(AppTypedConverters::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun appDao(): AppDao
}