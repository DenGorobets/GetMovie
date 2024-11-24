package com.den.gorobets.getmovie.ui.views.details

import android.graphics.Bitmap
import android.util.Log
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import cafe.adriel.voyager.navigator.Navigator
import com.den.gorobets.getmovie.R
import com.den.gorobets.getmovie.extensions.shareItem
import com.den.gorobets.getmovie.ui.elements.ColorizedColumn
import com.den.gorobets.getmovie.ui.elements.description_screens.movie_screen.MovieDescription
import com.den.gorobets.getmovie.ui.elements.error.FailureScreen
import com.den.gorobets.getmovie.ui.elements.loaders.MovieDescriptionLoadAnimation
import com.den.gorobets.getmovie.ui.elements.top_bars.DescriptionTopBar
import com.den.gorobets.getmovie.utils.data.DescriptionBottomPartData
import com.den.gorobets.getmovie.utils.data.DescriptionTopPartData
import com.den.gorobets.getmovie.utils.data.MovieSeriesItem
import com.den.gorobets.getmovie.utils.data.ReceivedDescriptionData
import com.den.gorobets.getmovie.utils.ui_state.DescriptionScreenUiState
import com.den.gorobets.getmovie.viewmodel.MovieDescriptionViewModel
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieDescriptionScreenView(
    navigator: Navigator,
    movieId: Int,
    viewModel: MovieDescriptionViewModel = koinViewModel { parametersOf(movieId) }
) {
    val context = LocalContext.current
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    val verticalScrollState = rememberScrollState()
    val snackbarHostState = remember { SnackbarHostState() }

    val shareMovieData = remember { mutableStateOf<List<String>>(emptyList()) }
    val saveMovie = remember { mutableStateOf(false) }
    val bitmapFromPoster = remember { mutableStateOf<Bitmap?>(null) }

    val viewState by viewModel.descriptionScreenViewState.collectAsState()

    Log.e("MovieDescriptionScreenView", "movieId: $movieId")

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            DescriptionTopBar(
                scrollBehavior = scrollBehavior,
                isItemStarred = saveMovie,
                navigationButton = {
                    navigator.pop()
                },
                shareButton = {
                    val presentText = context.getString(
                        R.string.hey_look_what_i_find_in_app_that_s_movie,
                        context.getString(R.string.app_name),
                        shareMovieData.value.getOrNull(1).orEmpty()
                    ) + shareMovieData.value.getOrNull(0).orEmpty()
                    context.shareItem(presentText)
                }
            )
        },
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) }
    ) { paddingValues ->
        ColorizedColumn(
            modifier = Modifier
                .fillMaxSize()
                .nestedScroll(scrollBehavior.nestedScrollConnection)
                .verticalScroll(verticalScrollState),
            bitmap = bitmapFromPoster,
            isColorTheme = viewState.isColorizedThemeEnabled,
            snackbarState = viewState.isSnackbarVisible,
            snackbarHostState = snackbarHostState,
            snackbarPerformed = {
                viewModel.setColorizedThemeEnabled(true)
                viewModel.setSnackbarVisibility()
            },
            snackbarDismissed = {
                viewModel.setColorizedThemeEnabled(false)
                viewModel.setSnackbarVisibility()
            }
        ) { themeColors ->
            when (val state = viewState.descriptionScreenUiState) {
                is DescriptionScreenUiState.Loading -> {
                    MovieDescriptionLoadAnimation(paddingValues)
                }

                is DescriptionScreenUiState.Error -> {
                    FailureScreen(
                        retryCallback = {
                            viewModel.retryFetchDescription()
                        }
                    )
                }

                is DescriptionScreenUiState.Success -> {
                    state.description?.let { description ->
                        shareMovieData.value = listOf(
                            stringResource(R.string.imdb_prefix_site, description.imdbID.orEmpty()),
                            description.title.orEmpty()
                        )

                        val similarMovie = description.similar?.results?.map { result ->
                            MovieSeriesItem(
                                poster = result.posterPath.orEmpty(),
                                title = result.title.orEmpty(),
                                imdbId = result.id
                            )
                        } ?: emptyList()

                        val descriptionTopPartData = DescriptionTopPartData(
                            poster = description.posterPath.orEmpty(),
                            backdrop = description.backdropPath ?: description.posterPath.orEmpty(),
                            title = description.title.orEmpty(),
                            tagline = description.tagline.orEmpty()
                        )

                        val descriptionBottomPartData = DescriptionBottomPartData(
                            runtime = description.runtime,
                            voteAverage = description.voteAverage,
                            releaseDate = description.releaseDate.orEmpty(),
                            genres = description.genres,
                            productionCountries = description.productionCountries ?: emptyList(),
                            crew = description.credits?.crew ?: emptyList(),
                            similar = similarMovie,
                            overview = description.overview.orEmpty(),
                            homepage = description.homepage.orEmpty(),
                            videoPath = description.videos
                        )

                        MovieDescription(
                            data = ReceivedDescriptionData(
                                descriptionTopPartData,
                                descriptionBottomPartData
                            ),
                            paddingValues = paddingValues,
                            navigator = navigator,
                            themeColors = themeColors,
                            receivedBitmap = bitmapFromPoster
                        )
                    }
                }
            }
        }
    }
}