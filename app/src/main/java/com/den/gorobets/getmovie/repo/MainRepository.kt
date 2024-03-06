package com.den.gorobets.getmovie.repo

import com.den.gorobets.getmovie.dto.description.movie.DescriptionMovieDTO
import com.den.gorobets.getmovie.dto.description.series.DescriptionSeriesDTO
import com.den.gorobets.getmovie.dto.now_playing.NowPlayingListMovieDTO
import com.den.gorobets.getmovie.dto.search.TrendingListDTO
import com.den.gorobets.getmovie.utils.Resource
import com.example.lesson1.data.pojo_tmdb.discover.DiscoverListMovieDTO
import com.example.lesson1.data.pojo_tmdb.discover.DiscoverListTVDTO
import com.example.lesson1.data.pojo_tmdb.person.DescriptionPersonDTO
import com.example.lesson1.data.pojo_tmdb.search.SearchListMovieDTO
import kotlinx.coroutines.flow.Flow

interface MainRepository {

    suspend fun getSearchedMovies(request: String): Flow<Resource<SearchListMovieDTO>>
    suspend fun getMovieDescription(movieId: Int): Flow<Resource<DescriptionMovieDTO>>
    suspend fun getSeriesDescription(seriesId: Int): Flow<Resource<DescriptionSeriesDTO>>
    suspend fun getPersonDescription(personId: Int): Flow<Resource<DescriptionPersonDTO>>
    suspend fun getNowPlayingMovies(): Flow<Resource<NowPlayingListMovieDTO>>
    suspend fun getTrending(): Flow<Resource<TrendingListDTO>>
    suspend fun getDiscoverTV(): Flow<Resource<DiscoverListTVDTO>>
    suspend fun getDiscoverMovie(): Flow<Resource<DiscoverListMovieDTO>>
}