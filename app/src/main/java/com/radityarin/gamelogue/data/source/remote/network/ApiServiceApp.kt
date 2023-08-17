package com.radityarin.gamelogue.data.source.remote.network

import com.radityarin.gamelogue.data.source.remote.network.EndpointConstants.API_KEY
import com.radityarin.gamelogue.data.source.remote.network.EndpointConstants.ENDPOINT_GAMES
import com.radityarin.gamelogue.data.source.remote.network.EndpointConstants.ENDPOINT_GAMES_WITH_ID
import com.radityarin.gamelogue.data.source.remote.network.EndpointConstants.PATH_ID
import com.radityarin.gamelogue.data.source.remote.network.EndpointConstants.QUERY_KEY
import com.radityarin.gamelogue.data.source.remote.network.EndpointConstants.QUERY_PAGE
import com.radityarin.gamelogue.data.source.remote.network.EndpointConstants.QUERY_PAGE_SIZE
import com.radityarin.gamelogue.data.source.remote.network.EndpointConstants.QUERY_SEARCH
import com.radityarin.gamelogue.data.source.remote.response.GameResponse
import com.radityarin.gamelogue.data.source.remote.response.GamelistResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiServiceApp {

    @GET(ENDPOINT_GAMES)
    suspend fun getAllGames(
        @Query(QUERY_KEY) key: String = API_KEY,
    ): GamelistResponse

    @GET(ENDPOINT_GAMES)
    suspend fun getAllGamesWithPagination(
        @Query(QUERY_PAGE) page: Int,
        @Query(QUERY_PAGE_SIZE) pageSize: Int,
        @Query(QUERY_KEY) key: String = API_KEY,
    ): GamelistResponse

    @GET(ENDPOINT_GAMES)
    suspend fun searchGames(
        @Query(QUERY_SEARCH) query: String,
        @Query(QUERY_KEY) key: String = API_KEY,
    ): GamelistResponse

    @GET(ENDPOINT_GAMES_WITH_ID)
    suspend fun getGamesDetail(
        @Path(PATH_ID) id: Int,
        @Query(QUERY_KEY) key: String = API_KEY,
    ): GameResponse

}