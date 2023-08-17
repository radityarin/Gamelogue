package com.radityarin.gamelogue.data.source.local.pref

import androidx.annotation.Keep
import com.chibatching.kotpref.KotprefModel

@Keep
object ProfilePrefs : KotprefModel() {

    var username by stringPref("")

}