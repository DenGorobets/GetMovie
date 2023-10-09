package com.den.gorobets.getmovie.ui.elements.search_view

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
import com.example.lesson1.data.pojo_tmdb.search.Result

@Composable
fun MovieScrollScreen(
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues,
    listState: LazyGridState,
    navController: Navigator,
    movieList: List<Result>
) {

    LazyVerticalGrid(
        state = listState,
        modifier = modifier,
        columns = GridCells.Adaptive(150.dp),
        contentPadding = PaddingValues(
            top = 80.dp,
            bottom = contentPadding.calculateBottomPadding(),
            start = 8.dp,
            end = 8.dp
        )
    ) {
        items(
            count = movieList.size,
            key = { movieList[it].id },
            itemContent = { index ->
                val movie = movieList[index]

                MovieCardItem(movieData = movie) {
                    navController.push(movie.navigationWaySelector())
                }
            })
    }
}
