package com.den.gorobets.getmovie.navigation.general_screen

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.screen.Screen
import com.den.gorobets.getmovie.ui.theme.GetMovieTheme
import com.den.gorobets.getmovie.ui.views.general.MovieSplashScreenView

object MovieSplashScreen : Screen {

    @Composable
    override fun Content() {

        GetMovieTheme {
            MovieSplashScreenView()
        }
    }
}