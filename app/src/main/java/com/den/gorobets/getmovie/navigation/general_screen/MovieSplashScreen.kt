package com.den.gorobets.getmovie.navigation.general_screen

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.screen.ScreenKey
import cafe.adriel.voyager.core.screen.uniqueScreenKey
import com.den.gorobets.getmovie.navigation.UniqueScreen
import com.den.gorobets.getmovie.ui.theme.GetMovieTheme
import com.den.gorobets.getmovie.ui.views.general.MovieSplashScreenView

object MovieSplashScreen : UniqueScreen() {

    override val key: ScreenKey = uniqueScreenKey

    @Composable
    override fun Content() {

        GetMovieTheme {
            MovieSplashScreenView()
        }
    }
}