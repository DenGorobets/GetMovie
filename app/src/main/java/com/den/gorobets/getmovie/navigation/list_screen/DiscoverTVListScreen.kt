package com.den.gorobets.getmovie.navigation.list_screen

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.screen.Screen
import com.den.gorobets.getmovie.ui.theme.GetMovieTheme
import com.den.gorobets.getmovie.ui.views.list.DiscoverTVListScreenView

object DiscoverTVListScreen : Screen {

    @Composable
    override fun Content() {
        GetMovieTheme {
            DiscoverTVListScreenView()
        }
    }
}