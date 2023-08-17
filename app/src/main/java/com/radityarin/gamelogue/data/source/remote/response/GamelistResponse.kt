package com.radityarin.gamelogue.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class GamelistResponse(
    @SerializedName("count")
    var count: Int = 0,
    @SerializedName("next")
    var next: String = "",
    @SerializedName("previous")
    var previous: String = "",
    @SerializedName("results")
    var gameResponses: List<GameResponse> = listOf()
)