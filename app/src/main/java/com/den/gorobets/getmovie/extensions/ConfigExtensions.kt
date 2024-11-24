package com.den.gorobets.getmovie.extensions

import cafe.adriel.voyager.core.screen.Screen
import com.den.gorobets.getmovie.navigation.description_screen.MovieDescriptionScreen
import com.den.gorobets.getmovie.navigation.description_screen.PersonDescriptionScreen
import com.den.gorobets.getmovie.navigation.description_screen.SerialDescriptionScreen
import com.den.gorobets.getmovie.dto.search.Result

fun Result.navigationWaySelector(): Screen {
    val navWay: Screen = when (mediaType) {
        "movie" -> MovieDescriptionScreen(id)
        "person" -> PersonDescriptionScreen(id)
        "tv" -> SerialDescriptionScreen(id)
        else -> MovieDescriptionScreen(id) //TODO: change to error screen
    }

    return navWay
}