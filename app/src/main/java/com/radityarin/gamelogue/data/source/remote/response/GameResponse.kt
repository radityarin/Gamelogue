package com.radityarin.gamelogue.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class GameResponse(
    @SerializedName("id")
    var id: Int = 0,
    @SerializedName("name")
    var name: String = "",
    @SerializedName("background_image")
    var backgroundImage: String = "",
    @SerializedName("released")
    var released: String = "",
    @SerializedName("rating")
    var rating: Double = 0.0,
    @SerializedName("rating_top")
    var ratingTop: Double = 0.0,
    @SerializedName("description")
    var description: String = "",
    @SerializedName("publishers")
    var publisher: List<PublisherResponse> = emptyList(),
    @SerializedName("added")
    var added: Double = 0.0
)

data class PublisherResponse(
    @SerializedName("name")
    var name: String = "",
)