package com.radityarin.gamelogue.utils.mapper

abstract class BaseMapper<T, R> {

    fun apply(oldItem: T): R {
        return map(oldItem)
    }

    fun apply(oldItem: List<T>): List<R> {
        return oldItem.map {
            apply(it)
        }
    }

    protected abstract fun map(oldItem: T): R
}
