package com.radityarin.gamelogue.utils.exts

import com.google.gson.Gson
import com.google.gson.GsonBuilder

fun getGsonInstance(): Gson {
    return GsonBuilder().disableHtmlEscaping().setPrettyPrinting().create()
}

fun Any.getGSONString(): String {
    return try {
        getGsonInstance().toJson(this)
    } catch (e: Exception) {
        ""
    }
}

fun String.addReleaseDate(): String {
    return "Release date $this"
}

fun String.addPlayed(): String {
    return "$this played"
}