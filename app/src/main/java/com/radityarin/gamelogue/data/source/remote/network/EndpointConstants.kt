package com.radityarin.gamelogue.data.source.remote.network

object EndpointConstants {

    const val BASE_URL = "https://api.rawg.io/api/"

    const val API_KEY = "552a9620fbfc47f1a8f44c2747e84f71"

    const val REQUEST_POST = "POST"
    const val REQUEST_GET = "GET"
    const val REQUEST_PUT = "PUT"
    const val REQUEST_DELETE = "DELETE"

    const val HEADER_AUTHORIZATION = "Authorization"
    const val HEADER_BEARER = "Bearer "

    const val QUERY_KEY = "key"
    const val QUERY_PAGE = "page"
    const val QUERY_PAGE_SIZE = "page"
    const val QUERY_SEARCH = "search"

    const val PATH_API_KEY = "YOUR_API_KEY"
    const val PATH_PAGE = "page"
    const val PATH_PAGE_SIZE = "count"
    const val PATH_QUERY = "query"
    const val PATH_ID = "id"

    const val ENDPOINT_GAMES = "games"
    const val ENDPOINT_GAMES_WITH_ID = "games/{$PATH_ID}"

}