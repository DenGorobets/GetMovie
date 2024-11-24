package com.den.gorobets.getmovie.ui.elements.shared

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.Navigator
import com.den.gorobets.getmovie.dto.discover.DiscoverListMovieDTO
import com.den.gorobets.getmovie.dto.discover.DiscoverListTVDTO
import com.den.gorobets.getmovie.dto.search.TrendingListDTO
import com.den.gorobets.getmovie.ui.elements.HorizontalMovieScrollerItem
import com.den.gorobets.getmovie.ui.elements.loaders.HorizontalMovieLoadAnimation
import com.den.gorobets.getmovie.utils.ResponseEvents
import com.den.gorobets.getmovie.utils.data.MovieSeriesItem

@Composable
fun ListLoader(
    modifier: Modifier = Modifier,
    responseEvents: ResponseEvents<*>,
    navigator: Navigator,
    errorCallback: (String) -> Unit,
    title: Int,
    moreButtonScreen: Screen,
    navigationIdSelector: (Int, Boolean) -> Screen
) {
    when (responseEvents) {
        is ResponseEvents.Failure -> {
            val error = responseEvents.error
            errorCallback.invoke(error)
        }

        is ResponseEvents.Loading -> {
            HorizontalMovieLoadAnimation()
        }

        is ResponseEvents.Success -> {
            val data = responseEvents.result

            data?.let { result ->
                val movies = mutableListOf<MovieSeriesItem>()

                when (result) {
                    is DiscoverListMovieDTO -> {
                        result.results?.map { movieResult ->
                            movies += MovieSeriesItem(
                                poster = movieResult.posterPath.orEmpty(),
                                title = movieResult.title.orEmpty(),
                                imdbId = movieResult.id
                            )
                        }
                    }

                    is DiscoverListTVDTO -> {
                        result.results.map { tvResult ->
                            movies += MovieSeriesItem(
                                poster = tvResult.posterPath.orEmpty(),
                                title = tvResult.name.orEmpty(),
                                imdbId = tvResult.id,
                                isTVSeries = true
                            )
                        }
                    }

                    is TrendingListDTO -> {
                        result.results?.map { trendingResult ->
                            movies += MovieSeriesItem(
                                poster = trendingResult.posterPath.orEmpty(),
                                title = trendingResult.title ?: trendingResult.name.orEmpty(),
                                imdbId = trendingResult.id,
                                isTVSeries = !trendingResult.name.isNullOrBlank()
                            )
                        }
                    }
                }

                HorizontalMovieScrollerItem(
                    modifier = modifier,
                    label = stringResource(title),
                    data = movies,
                    moreButton = {
                        navigator.push(moreButtonScreen)
                    },
                    navigationId = { count, index ->
                        navigator.push(navigationIdSelector(index, movies[count].isTVSeries))
                    }
                )
            }
        }
    }
}