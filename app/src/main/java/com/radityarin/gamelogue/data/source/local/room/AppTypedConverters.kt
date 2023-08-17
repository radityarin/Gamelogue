package com.radityarin.gamelogue.data.source.local.room

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.radityarin.gamelogue.data.source.local.entity.store.GameEntity
import java.lang.reflect.Type

class AppTypedConverters {

    @TypeConverter
    fun fromStringToGameEntity(data: String?): GameEntity? {
        val gson = Gson()
        if (data == null) {
            return null
        }
        val listType: Type = object : TypeToken<GameEntity?>() {}.type
        return gson.fromJson<GameEntity>(data, listType)
    }

    @TypeConverter
    fun fromGameEntityToString(myObjects: GameEntity?): String? {
        val gson = Gson()
        return gson.toJson(myObjects)
    }

}