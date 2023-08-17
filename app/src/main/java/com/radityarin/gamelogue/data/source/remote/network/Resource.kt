package com.radityarin.gamelogue.data.source.remote.network

class Resource<T>(val status: Any) {

    var item: T? = null

    private var throwable: Throwable? = null

    constructor(status: Any, item: T?) : this(status) {
        this.item = item
    }

    constructor(status: Any, throwable: Throwable) : this(status) {
        this.throwable = throwable
    }
}
