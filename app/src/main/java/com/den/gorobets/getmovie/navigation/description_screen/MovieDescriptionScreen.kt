package com.den.gorobets.getmovie.navigation.description_screen

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.screen.ScreenKey
import cafe.adriel.voyager.core.screen.uniqueScreenKey
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.den.gorobets.getmovie.navigation.UniqueScreen
import com.den.gorobets.getmovie.ui.theme.GetMovieTheme
import com.den.gorobets.getmovie.ui.views.details.MovieDescriptionScreenView

data class MovieDescriptionScreen(private val movieId: Int) : UniqueScreen() {

    override val key: ScreenKey = uniqueScreenKey

    @Composable
    override fun Content() {

        val navigator = LocalNavigator.currentOrThrow

        GetMovieTheme {
            MovieDescriptionScreenView(navigator, movieId)
        }
    }
}