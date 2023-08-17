package com.radityarin.gamelogue.utils.exts

import android.app.Activity
import android.content.Intent
import com.radityarin.gamelogue.domain.model.Game
import com.radityarin.gamelogue.presentation.menu.MainActivity
import com.radityarin.gamelogue.presentation.menu.details.DetailGameActivity

fun Activity.goToMainActivity() {
    startActivity(
        Intent(
            this,
            MainActivity::class.java
        )
    )
}

fun Activity.goToDetail(game: Game) {
    startActivity(
        Intent(
            this,
            DetailGameActivity::class.java
        ).apply {
            setGame(game)
        }
    )
}
