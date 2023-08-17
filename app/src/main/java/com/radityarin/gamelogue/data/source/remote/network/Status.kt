package com.radityarin.gamelogue.data.source.remote.network

import retrofit2.HttpException

sealed class Status<T>(val data: T? = null, val message: String? = null, val messageId: Int = 0, val errorCode: String? = null, val errorData: String? = null, val httpError: HttpException? = null, val chickinError: Any? = null) {
    class Success<T>(data: T) : Status<T>(data)
    class Loading<T>(data: T? = null) : Status<T>(data)
    class Error<T>(message: String, errorCode: String? = null, errorData: String? = null, messageId: Int = 0, data: T? = null) : Status<T>(data, message, messageId, errorCode, errorData)
    class HttpError<T>(httpError: HttpException, data: T? = null): Status<T>(data, message = httpError.message(), httpError = httpError)
}