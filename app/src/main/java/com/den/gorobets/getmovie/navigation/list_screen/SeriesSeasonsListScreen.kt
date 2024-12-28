package com.den.gorobets.getmovie.navigation.list_screen

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.screen.ScreenKey
import cafe.adriel.voyager.core.screen.uniqueScreenKey
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.den.gorobets.getmovie.dto.description.ScrollerItemData
import com.den.gorobets.getmovie.navigation.UniqueScreen
import com.den.gorobets.getmovie.ui.theme.GetMovieTheme
import com.den.gorobets.getmovie.ui.views.list.SeriesSeasonsListScreenView

data class SeriesSeasonsListScreen(private val seasonList: List<ScrollerItemData>) : UniqueScreen() {

    override val key: ScreenKey = uniqueScreenKey

    @Composable
    override fun Content() {

        val navigator = LocalNavigator.currentOrThrow

        GetMovieTheme {
            SeriesSeasonsListScreenView(navigator, seasonList)
        }
    }
}