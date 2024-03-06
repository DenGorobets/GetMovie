package com.den.gorobets.getmovie.navigation

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.screen.Screen
import com.den.gorobets.getmovie.ui.theme.GetMovieTheme
import com.den.gorobets.getmovie.ui.views.MovieDescriptionScreenView

data class MovieDescriptionScreen(val movieId: Int) : Screen {

    @Composable
    override fun Content() {
        GetMovieTheme {
            MovieDescriptionScreenView()
        }
    }
}