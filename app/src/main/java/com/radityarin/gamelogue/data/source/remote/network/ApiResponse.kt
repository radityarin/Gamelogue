package com.radityarin.gamelogue.data.source.remote.network

import retrofit2.HttpException

sealed class ApiResponse<out R> {
    data class Success<out T>(val data: T) : ApiResponse<T>()
    data class Error(val errorMessage: String = "", val errorMessageID: Int = 0, val errorCode: String = "", val errorData: String = "") : ApiResponse<Nothing>()
    data class HttpError(val httpError: HttpException): ApiResponse<Nothing>()
}