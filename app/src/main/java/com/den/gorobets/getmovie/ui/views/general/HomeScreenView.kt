package com.den.gorobets.getmovie.ui.views.general

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import cafe.adriel.voyager.navigator.Navigator
import com.den.gorobets.getmovie.R
import com.den.gorobets.getmovie.dto.now_playing.NowPlayingListMovieDTO
import com.den.gorobets.getmovie.dto.search.TrendingListDTO
import com.den.gorobets.getmovie.extensions.navigationWaySelector
import com.den.gorobets.getmovie.navigation.description_screen.MovieDescriptionScreen
import com.den.gorobets.getmovie.navigation.description_screen.SerialDescriptionScreen
import com.den.gorobets.getmovie.navigation.list_screen.DiscoverMovieListScreen
import com.den.gorobets.getmovie.navigation.list_screen.TrendingListScreen
import com.den.gorobets.getmovie.ui.elements.HorizontalMovieScrollerItem
import com.den.gorobets.getmovie.ui.elements.MovieScrollerItem
import com.den.gorobets.getmovie.ui.elements.error.FailureScreen
import com.den.gorobets.getmovie.ui.elements.home_view.HomeScreenItems
import com.den.gorobets.getmovie.ui.elements.home_view.MoviePager
import com.den.gorobets.getmovie.ui.elements.home_view.SeeAllButton
import com.den.gorobets.getmovie.ui.elements.loaders.HorizontalMovieLoadAnimation
import com.den.gorobets.getmovie.ui.elements.loaders.MoviePageLoadAnimation
import com.den.gorobets.getmovie.ui.elements.top_bars.HomeTopBar
import com.den.gorobets.getmovie.utils.ResponseEvents
import com.den.gorobets.getmovie.viewmodel.HomeViewModel
import com.example.lesson1.data.pojo_tmdb.discover.DiscoverListMovieDTO
import com.example.lesson1.data.pojo_tmdb.discover.DiscoverListTVDTO
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreenView(navigator: Navigator, viewModel: HomeViewModel = koinViewModel()) {

    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    val verticalScrollState = rememberScrollState()
    val snackbarHostState = remember { SnackbarHostState() }

    val nowPlayingListItems by viewModel.nowPlayingMoviesList.collectAsStateWithLifecycle(
        initialValue = ResponseEvents.Loading()
    )
    val trendingListItems by viewModel.nowTrendingList.collectAsStateWithLifecycle(initialValue = ResponseEvents.Loading())
    val discoverTVListItems by viewModel.discoverTVList.collectAsStateWithLifecycle(initialValue = ResponseEvents.Loading())
    val discoverMovieListItems by viewModel.discoverMovieList.collectAsStateWithLifecycle(
        initialValue = ResponseEvents.Loading()
    )

    val textError = remember { mutableStateOf("Error") }
    val visibleErrorScreen = remember { mutableStateOf(false) }

    LaunchedEffect(visibleErrorScreen.value) {
        if (visibleErrorScreen.value) {
            verticalScrollState.animateScrollTo(0)
            snackbarHostState.showSnackbar(textError.value)
        }
    }

    Scaffold(modifier = Modifier.fillMaxSize(),
        topBar = { HomeTopBar(scrollBehavior = scrollBehavior) },
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        }) { paddingValues ->

        Box {
            if (!visibleErrorScreen.value)
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .nestedScroll(scrollBehavior.nestedScrollConnection)
                        .verticalScroll(verticalScrollState)
                ) {

                    Spacer(Modifier.padding(top = paddingValues.calculateTopPadding() + 16.dp))
                    HomeScreenItems(
                        nowPlayingListItems,
                        trendingListItems,
                        discoverTVListItems,
                        discoverMovieListItems,
                        navigator
                    ) {
                        textError.value = it
                        if (!visibleErrorScreen.value) visibleErrorScreen.value = true
                    }
                    Spacer(Modifier.padding(bottom = paddingValues.calculateBottomPadding()))
                }
            else
                Column(
                    modifier = Modifier.fillMaxSize()
                ) {
                    FailureScreen(
                        Modifier.fillMaxSize(),
                        retryCallback = {
                            visibleErrorScreen.value = false
                            viewModel.retryRequests()
                        })
                }
        }
    }
}

@Composable
fun DiscoverMovieListLoader(
    modifier: Modifier = Modifier,
    discoverMovieListItems: ResponseEvents<DiscoverListMovieDTO>,
    navigator: Navigator,
    errorCallback: (String) -> Unit
) {

    when (discoverMovieListItems) {
        is ResponseEvents.Empty -> {}
        is ResponseEvents.Failure -> {
            val error = discoverMovieListItems.error
            errorCallback.invoke(error)
        }

        is ResponseEvents.Loading -> {
            HorizontalMovieLoadAnimation()
        }

        is ResponseEvents.Success -> {
            val data = discoverMovieListItems.result

            HorizontalMovieScrollerItem(modifier = modifier,
                stringResource(R.string.new_movies),
                data?.results ?: emptyList(),
                itemContent = { index ->
                    MovieScrollerItem(
                        index.posterPath.orEmpty(), index.title.orEmpty()
                    ) {
                        navigator.push(MovieDescriptionScreen(index.id))
                    }
                },
                addButton = {
                    SeeAllButton {
                        navigator.push(DiscoverMovieListScreen)
                    }
                })
        }
    }
}

@Composable
fun DiscoverTVListLoader(
    modifier: Modifier = Modifier,
    discoverTVListItems: ResponseEvents<DiscoverListTVDTO>,
    navigator: Navigator,
    errorCallback: (String) -> Unit
) {

    when (discoverTVListItems) {
        is ResponseEvents.Empty -> {}
        is ResponseEvents.Failure -> {
            val error = discoverTVListItems.error
            errorCallback.invoke(error)
        }

        is ResponseEvents.Loading -> {
            HorizontalMovieLoadAnimation()
        }

        is ResponseEvents.Success -> {
            val data = discoverTVListItems.result

            HorizontalMovieScrollerItem(modifier = modifier,
                stringResource(R.string.new_tv_series),
                data?.results ?: emptyList(),
                itemContent = { index ->
                    MovieScrollerItem(
                        index.posterPath.orEmpty(), index.name.orEmpty()
                    ) {
                        navigator.push(SerialDescriptionScreen(index.id))
                    }
                },
                addButton = {
                    SeeAllButton {
                        navigator.push(DiscoverMovieListScreen)
                    }
                })
        }
    }
}

@Composable
fun TrendingListLoader(
    modifier: Modifier = Modifier,
    trendingListItems: ResponseEvents<TrendingListDTO>,
    navigator: Navigator,
    errorCallback: (String) -> Unit
) {

    when (trendingListItems) {
        is ResponseEvents.Empty -> {}
        is ResponseEvents.Failure -> {
            val error = trendingListItems.error
            errorCallback.invoke(error)
        }

        is ResponseEvents.Loading -> {
            HorizontalMovieLoadAnimation()
        }

        is ResponseEvents.Success -> {
            val data = trendingListItems.result

            HorizontalMovieScrollerItem(modifier = modifier,
                stringResource(R.string.now_in_trends),
                data?.results ?: emptyList(),
                itemContent = { index ->
                    MovieScrollerItem(
                        index.posterPath.orEmpty(), index.title ?: index.name.orEmpty()
                    ) {
                        navigator.push(index.navigationWaySelector())
                    }
                },
                addButton = {
                    SeeAllButton {
                        navigator.push(TrendingListScreen)
                    }
                })
        }
    }
}

@Composable
fun MoviePagerLoader(
    modifier: Modifier = Modifier,
    nowPlayingListItems: ResponseEvents<NowPlayingListMovieDTO>,
    navigator: Navigator,
    errorCallback: (String) -> Unit
) {

    when (nowPlayingListItems) {
        is ResponseEvents.Empty -> {}
        is ResponseEvents.Failure -> {
            val error = nowPlayingListItems.error
            errorCallback.invoke(error)
        }

        is ResponseEvents.Loading -> {
            MoviePageLoadAnimation()
        }

        is ResponseEvents.Success -> {
            val data = nowPlayingListItems.result

            MoviePager(
                modifier = modifier,
                movies = data?.resultNowPlayingMovies ?: emptyList()
            ) {
                navigator.push(MovieDescriptionScreen(it))
            }
        }
    }
}

//@Preview(showBackground = true)
//@Composable
//fun HomeScreenPreview() {
//    GetMovieTheme {
//        HomeScreenView()
//    }
//}