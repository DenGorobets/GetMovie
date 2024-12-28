package com.den.gorobets.getmovie.ui.elements.home_screen

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.navigator.Navigator
import com.den.gorobets.getmovie.R
import com.den.gorobets.getmovie.dto.discover.DiscoverListMovieDTO
import com.den.gorobets.getmovie.dto.discover.DiscoverListTVDTO
import com.den.gorobets.getmovie.dto.now_playing.NowPlayingListMovieDTO
import com.den.gorobets.getmovie.dto.search.TrendingListDTO
import com.den.gorobets.getmovie.navigation.description_screen.MovieDescriptionScreen
import com.den.gorobets.getmovie.navigation.description_screen.SeriesDescriptionScreen
import com.den.gorobets.getmovie.navigation.list_screen.DiscoverMovieListScreen
import com.den.gorobets.getmovie.navigation.list_screen.DiscoverTVListScreen
import com.den.gorobets.getmovie.navigation.list_screen.TrendingListScreen
import com.den.gorobets.getmovie.ui.elements.shared.ListLoader
import com.den.gorobets.getmovie.utils.ResponseEvents

@Composable
fun HomeScreenItems(
    nowPlayingListItems: ResponseEvents<NowPlayingListMovieDTO>,
    trendingListItems: ResponseEvents<TrendingListDTO>,
    discoverTVListItems: ResponseEvents<DiscoverListTVDTO>,
    discoverMovieListItems: ResponseEvents<DiscoverListMovieDTO>,
    navigator: Navigator,
    callback: (String) -> Unit
) {

    MoviePagerLoader(Modifier, nowPlayingListItems, navigator) {
        callback.invoke(it)
    }

    ListLoader(
        responseEvents = trendingListItems,
        navigator = navigator,
        errorCallback = callback,
        title = R.string.now_in_trends,
        moreButtonScreen = TrendingListScreen,
        navigationIdSelector = { index, isSeries ->
            if (isSeries)
                SeriesDescriptionScreen(index)
            else
                MovieDescriptionScreen(index)
        }
    )

    ListLoader(
        responseEvents = discoverTVListItems,
        navigator = navigator,
        errorCallback = callback,
        title = R.string.new_tv_series,
        moreButtonScreen = DiscoverMovieListScreen,
        navigationIdSelector = { index, _ ->
            SeriesDescriptionScreen(index)
        }
    )

    ListLoader(
        responseEvents = discoverMovieListItems,
        navigator = navigator,
        errorCallback = callback,
        title = R.string.new_movies,
        moreButtonScreen = DiscoverTVListScreen,
        navigationIdSelector = { index, _ ->
            MovieDescriptionScreen(index)
        }
    )
}