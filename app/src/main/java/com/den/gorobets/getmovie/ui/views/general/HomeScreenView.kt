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
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import cafe.adriel.voyager.navigator.Navigator
import com.den.gorobets.getmovie.navigation.general_screen.SearchScreen
import com.den.gorobets.getmovie.ui.elements.error.FailureScreen
import com.den.gorobets.getmovie.ui.elements.home_screen.HomeScreenItems
import com.den.gorobets.getmovie.ui.elements.top_bars.HomeTopBar
import com.den.gorobets.getmovie.utils.ResponseEvents
import com.den.gorobets.getmovie.viewmodel.HomeViewModel
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreenView(
    navigator: Navigator,
    viewModel: HomeViewModel = koinViewModel()
) {

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

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            HomeTopBar(scrollBehavior = scrollBehavior) {
                navigator.push(SearchScreen)
            }
        },
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        }
    ) { paddingValues ->

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
                        retryCallback = {
                            visibleErrorScreen.value = false
                            viewModel.retryRequests()
                        })
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