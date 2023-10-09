package com.den.gorobets.getmovie.extensions

import android.content.Context
import androidx.core.os.ConfigurationCompat
import cafe.adriel.voyager.core.screen.Screen
import com.den.gorobets.getmovie.navigation.MovieDescriptionScreen
import com.den.gorobets.getmovie.navigation.PersonDescriptionScreen
import com.den.gorobets.getmovie.navigation.SerialDescriptionScreen
import com.example.lesson1.data.pojo_tmdb.search.Result

fun Context.getCurrentLanguage(): String =
    ConfigurationCompat.getLocales(resources.configuration)[0]?.language ?: ""

fun Result.navigationWaySelector(): Screen {
    val navWay: Screen = when (mediaType) {
        "movie" -> MovieDescriptionScreen(id)
        "person" -> PersonDescriptionScreen(id)
        "tv" -> SerialDescriptionScreen(id)
        else -> MovieDescriptionScreen(id) //TODO: change to error screen
    }

    return navWay
}