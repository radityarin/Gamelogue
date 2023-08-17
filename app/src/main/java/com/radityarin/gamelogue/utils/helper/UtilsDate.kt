package com.radityarin.gamelogue.utils.helper

import java.text.SimpleDateFormat
import java.util.*

object UtilsDate {

    fun getCurrentDateTimeISO(): String {
        val df = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault())
        return df.format(Date())
    }

}