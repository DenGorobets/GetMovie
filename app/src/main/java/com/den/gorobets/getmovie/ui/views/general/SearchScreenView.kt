package com.den.gorobets.getmovie.ui.views.general

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import cafe.adriel.voyager.navigator.Navigator
import com.den.gorobets.getmovie.ui.elements.ScrollableSearchBar
import com.den.gorobets.getmovie.ui.elements.error.FailureScreen
import com.den.gorobets.getmovie.ui.elements.search_screen.MovieScrollScreen
import com.den.gorobets.getmovie.utils.ResponseEvents
import com.den.gorobets.getmovie.viewmodel.SearchMovieViewModel
import com.den.gorobets.getmovie.dto.search.SearchListMovieDTO
import org.koin.androidx.compose.koinViewModel

@Composable
fun SearchScreenView(navigator: Navigator, viewModel: SearchMovieViewModel = koinViewModel()) {

    val listState = rememberLazyGridState()
    val searchFieldText = rememberSaveable { mutableStateOf("") }
    val snackbarHostState = remember { SnackbarHostState() }
    val textError = remember { mutableStateOf("Error") }
    val visibleErrorScreen = remember { mutableStateOf(false) }

    val scrollUpState by viewModel.scrollUp.collectAsState()
    val firstItemIsVisible by remember { derivedStateOf { listState.firstVisibleItemIndex } }

    val movieItems by viewModel.searchMovies.collectAsStateWithLifecycle(initialValue = ResponseEvents.Loading())


    viewModel.updateScrollPosition(firstItemIsVisible)

    LaunchedEffect(searchFieldText.value.length) {
        viewModel.geSearchedMovies(searchFieldText.value)

        listState.scrollToItem(firstItemIsVisible)
        if (visibleErrorScreen.value)
            snackbarHostState.showSnackbar(textError.value)
    }

    Scaffold(
        modifier = Modifier,
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) }
    ) { padding ->
        ScrollableSearchBar(scrollUpState = scrollUpState, searchFieldText = searchFieldText)

        when (movieItems) {
            is ResponseEvents.Loading -> {
                Text(modifier = Modifier.fillMaxSize(), text = "Loading")
            }

            is ResponseEvents.Failure -> {
                textError.value =
                    (movieItems as ResponseEvents.Failure<SearchListMovieDTO>).error
                visibleErrorScreen.value = true

                FailureScreen(
                    retryCallback = {
                        viewModel.geSearchedMovies(searchFieldText.value)
                        visibleErrorScreen.value = false
                    })
            }

            is ResponseEvents.Success -> {
                val data =
                    (movieItems as ResponseEvents.Success<SearchListMovieDTO>).result

                MovieScrollScreen(
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = padding,
                    listState = listState,
                    navController = navigator,
                    movieList = data
                )
            }
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