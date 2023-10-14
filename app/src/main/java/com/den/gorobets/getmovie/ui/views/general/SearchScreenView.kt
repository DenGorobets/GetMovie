package com.den.gorobets.getmovie.ui.views.general

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.navigator.Navigator
import com.den.gorobets.getmovie.ui.elements.ScrollableSearchBar
import com.den.gorobets.getmovie.viewmodel.SearchMovieViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun SearchScreenView(navigator: Navigator, viewModel: SearchMovieViewModel = koinViewModel()) {

    val listState = rememberLazyGridState()
    val searchFieldText = rememberSaveable { mutableStateOf("") }
//    val movieItems by viewModel.searchMovies(searchFieldText.value)
//        .observeAsState()
    val scrollUpState by viewModel.scrollUp.collectAsState()
    val firstItemIsVisible by remember { derivedStateOf { listState.firstVisibleItemIndex } }

    viewModel.updateScrollPosition(firstItemIsVisible)

    Scaffold(modifier = Modifier) { padding ->
        Box {
            ScrollableSearchBar(scrollUpState = scrollUpState, searchFieldText = searchFieldText)
//            MovieScrollScreen(
//                modifier = Modifier.fillMaxSize(),
//                contentPadding = padding,
//                listState = listState,
//                navController = navigator,
//                movieList = movieItems?.results ?: emptyList()
//            )
        }
    }
}

//@Preview(showBackground = true)
//@Composable
//fun SearchScreenPreview() {
//    GetMovieTheme {
//        SearchScreenView()
//    }
//}