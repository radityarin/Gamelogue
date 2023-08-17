package com.radityarin.gamelogue.utils.exts

import android.content.Intent
import com.google.gson.Gson
import com.radityarin.gamelogue.domain.model.Game

const val GAME = "GAME"

fun Intent.setGame(model: Game) {
    putExtra(GAME, model.getGSONString())
}

fun Intent.getGame(): Game {
    return if (hasExtra(GAME)) {
        val dataAsString = getStringExtra(GAME) ?: ""
        try {
            Gson().fromJson(dataAsString, Game::class.java)
        } catch (e: Exception) {
            Game()
        }
    } else {
        Game()
    }
}