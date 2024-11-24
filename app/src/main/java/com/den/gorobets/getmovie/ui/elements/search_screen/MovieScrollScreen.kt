package com.den.gorobets.getmovie.ui.elements.search_screen

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.navigator.Navigator
import com.den.gorobets.getmovie.extensions.navigationWaySelector
import com.den.gorobets.getmovie.ui.elements.shared.MovieCardItem
import com.den.gorobets.getmovie.dto.search.SearchListMovieDTO

@Composable
fun MovieScrollScreen(
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues,
    listState: LazyGridState,
    navController: Navigator,
    movieList: SearchListMovieDTO?
) {

    movieList?.let { data ->

        LazyVerticalGrid(
            state = listState,
            modifier = modifier,
            columns = GridCells.Adaptive(150.dp),
            contentPadding = PaddingValues(
                top = contentPadding.calculateTopPadding() + 80.dp,
                bottom = contentPadding.calculateBottomPadding(),
                start = 8.dp,
                end = 8.dp
            )
        ) {
            items(
                count = data.results.size,
                key = { data.results[it].id },
                itemContent = { index ->
                    val movie = data.results[index]

                    MovieCardItem(movieData = movie) {
                        navController.push(movie.navigationWaySelector())
                    }
                })
        }
    }
}
