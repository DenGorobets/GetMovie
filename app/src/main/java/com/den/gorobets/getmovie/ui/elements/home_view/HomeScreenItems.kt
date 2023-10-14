package com.den.gorobets.getmovie.ui.elements.home_view

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.navigator.Navigator
import com.den.gorobets.getmovie.dto.now_playing.NowPlayingListMovieDTO
import com.den.gorobets.getmovie.dto.search.TrendingListDTO
import com.den.gorobets.getmovie.ui.views.general.DiscoverMovieListLoader
import com.den.gorobets.getmovie.ui.views.general.DiscoverTVListLoader
import com.den.gorobets.getmovie.ui.views.general.MoviePagerLoader
import com.den.gorobets.getmovie.ui.views.general.TrendingListLoader
import com.den.gorobets.getmovie.utils.ResponseEvents
import com.example.lesson1.data.pojo_tmdb.discover.DiscoverListMovieDTO
import com.example.lesson1.data.pojo_tmdb.discover.DiscoverListTVDTO

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
    TrendingListLoader(Modifier, trendingListItems, navigator) {
        callback.invoke(it)
    }
    DiscoverTVListLoader(Modifier, discoverTVListItems, navigator) {
        callback.invoke(it)
    }
    DiscoverMovieListLoader(Modifier, discoverMovieListItems, navigator) {
        callback.invoke(it)
    }
}