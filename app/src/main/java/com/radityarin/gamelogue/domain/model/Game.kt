package com.radityarin.gamelogue.domain.model

data class Game(
    var id: Int = 0,
    var name: String = "",
    var backgroundImage: String = "",
    var released: String = "",
    var rating: Double = 0.0,
    var ratingTop: Double = 0.0,
    var isFavorite: Boolean = false,
    var description: String = "",
    var publisher: String = "",
    var added: Double = 0.0
)