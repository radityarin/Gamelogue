package com.radityarin.gamelogue.data.source.local.entity.store

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "game")
data class GameEntity(
    @PrimaryKey
    @ColumnInfo(name = "id")
    var id: Int = 0,
    @ColumnInfo(name = "name")
    var name: String = "",
    @ColumnInfo(name = "backgroundImage")
    var backgroundImage: String = "",
    @ColumnInfo(name = "released")
    var released: String = "",
    @ColumnInfo(name = "rating")
    var rating: Double = 0.0,
    @ColumnInfo(name = "ratingTop")
    var ratingTop: Double = 0.0,
    @ColumnInfo(name = "description")
    var description: String = "",
    @ColumnInfo(name = "publisher")
    var publisher: String = "",
    @ColumnInfo(name = "added")
    var added: Double = 0.0
)