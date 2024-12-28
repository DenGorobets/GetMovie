package com.den.gorobets.getmovie.ui.views.details

import android.graphics.Bitmap
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
import com.den.gorobets.getmovie.dto.description.series.SerialSeasonItem
import com.den.gorobets.getmovie.extensions.shareItem
import com.den.gorobets.getmovie.extensions.toMovieScrollerData
import com.den.gorobets.getmovie.extensions.toSeasonScrollerData
import com.den.gorobets.getmovie.ui.elements.ColorizedColumn
import com.den.gorobets.getmovie.ui.elements.description_screens.movie_screen.SeriesDescription
import com.den.gorobets.getmovie.ui.elements.error.FailureScreen
import com.den.gorobets.getmovie.ui.elements.loaders.MovieDescriptionLoadAnimation
import com.den.gorobets.getmovie.ui.elements.top_bars.DescriptionTopBar
import com.den.gorobets.getmovie.utils.data.DescriptionTopPartData
import com.den.gorobets.getmovie.utils.data.MovieSeriesPersonItem
import com.den.gorobets.getmovie.utils.data.ReceivedSeriesDescriptionData
import com.den.gorobets.getmovie.utils.data.SeriesBottomPartData
import com.den.gorobets.getmovie.utils.ui_state.SeriesScreenUiState
import com.den.gorobets.getmovie.viewmodel.SeriesDescriptionViewModel
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SeriesDescriptionScreenView(
    navigator: Navigator,
    movieId: Int,
    viewModel: SeriesDescriptionViewModel = koinViewModel { parametersOf(movieId) }
) {
    val context = LocalContext.current
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    val verticalScrollState = rememberScrollState()
    val snackbarHostState = remember { SnackbarHostState() }

    val shareSeriesData = remember { mutableStateOf<List<String>>(emptyList()) }
    val saveSeries = remember { mutableStateOf(false) }
    val bitmapFromPoster = remember { mutableStateOf<Bitmap?>(null) }

    val viewState by viewModel.seriesScreenViewState.collectAsState()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            DescriptionTopBar(
                scrollBehavior = scrollBehavior,
                isItemStarred = saveSeries,
                navigationButton = {
                    navigator.pop()
                },
                shareButton = {
                    val presentText = context.getString(
                        R.string.hey_look_what_i_find_in_app_that_s_series,
                        context.getString(R.string.app_name),
                        shareSeriesData.value.getOrNull(1).orEmpty()
                    ) + shareSeriesData.value.getOrNull(0).orEmpty()
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
            when (val state = viewState.seriesScreenUiState) {
                is SeriesScreenUiState.Loading -> {
                    MovieDescriptionLoadAnimation(paddingValues)
                }

                is SeriesScreenUiState.Error -> {
                    FailureScreen(
                        retryCallback = {
                            viewModel.retryFetchDescription()
                        }
                    )
                }

                is SeriesScreenUiState.Success -> {
                    state.description?.let { description ->
                        shareSeriesData.value = listOf(
                            stringResource(R.string.imdb_prefix_site, description.imdbID.orEmpty()),
                            description.name.orEmpty()
                        )

                        val similarMovie = description.similar?.results?.map { result ->
                            MovieSeriesPersonItem(
                                poster = result.posterPath.orEmpty(),
                                title = result.title.orEmpty(),
                                imdbId = result.id
                            )
                        } ?: emptyList()

                        val seasonItemList = description.seasons?.map { result ->
                            SerialSeasonItem(
                                airDate = result.airDate,
                                episodeCount = result.episodeCount,
                                name = result.name.orEmpty(),
                                overview = result.overview,
                                posterPath = result.posterPath.orEmpty(),
                                seasonNumber = result.seasonNumber,
                                imdbId = result.id
                            )
                        } ?: emptyList()

                        val descriptionTopPartData = DescriptionTopPartData(
                            poster = description.posterPath.orEmpty(),
                            backdrop = description.backdropPath ?: description.posterPath.orEmpty(),
                            title = description.name.orEmpty(),
                            tagline = description.tagline.orEmpty()
                        )

                        val seriesBottomPartData = SeriesBottomPartData(
                            numberOfEpisodes = description.numberOfEpisodes,
                            numberOfSeasons = description.numberOfSeasons,
                            seriesStatus = description.status.orEmpty(),
                            inProduction = description.inProduction ?: false,
                            episodeRuntime = description.episodeRunTime ?: emptyList(),
                            firstAirDate = description.firstAirDate.orEmpty(),
                            lastAirDate = description.lastAirDate.orEmpty(),
                            nextEpisodeToAir = description.nextEpisodeToAir?.airDate.orEmpty(),
                            lastEpisodeToAir = description.lastEpisodeToAir?.airDate.orEmpty(),
                            voteAverage = description.voteAverage,
                            genres = description.genres,
                            productionCountries = description.productionCountries ?: emptyList(),
                            crew = description.credits?.crew ?: emptyList(),
                            similar = similarMovie.toMovieScrollerData(),
                            seasonsList = seasonItemList.toSeasonScrollerData(),
                            overview = description.overview.orEmpty(),
                            homepage = description.homepage.orEmpty(),
                            videoPath = description.videos
                        )

                        SeriesDescription(
                            data = ReceivedSeriesDescriptionData(
                                descriptionTopPartData,
                                seriesBottomPartData
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

//@Preview(showBackground = true)
//@Composable
//fun SerialDescriptionScreenPreview() {
//    GetMovieTheme {
//        SerialDescriptionScreenView()
//    }
//}