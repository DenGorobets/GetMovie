package com.den.gorobets.getmovie.navigation.list_screen

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.screen.Screen
import com.den.gorobets.getmovie.ui.theme.GetMovieTheme
import com.den.gorobets.getmovie.ui.views.list.DiscoverMovieListScreenView

object DiscoverMovieListScreen : Screen {

    @Composable
    override fun Content() {
        GetMovieTheme {
            DiscoverMovieListScreenView()
        }
    }
}