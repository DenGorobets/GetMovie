package com.den.gorobets.getmovie.repo

import com.den.gorobets.getmovie.api.RetrofitServices
import com.den.gorobets.getmovie.dto.description.movie.DescriptionMovieDTO
import com.den.gorobets.getmovie.dto.description.series.DescriptionSeriesDTO
import com.den.gorobets.getmovie.dto.now_playing.NowPlayingListMovieDTO
import com.den.gorobets.getmovie.dto.search.TrendingListDTO
import com.example.lesson1.data.pojo_tmdb.discover.DiscoverListMovieDTO
import com.example.lesson1.data.pojo_tmdb.discover.DiscoverListTVDTO
import com.example.lesson1.data.pojo_tmdb.person.DescriptionPersonDTO
import com.example.lesson1.data.pojo_tmdb.search.SearchListMovieDTO

class MainRepositoryImpl(private val movieRepo: RetrofitServices) : MainRepository {

    override suspend fun getSearchedMovies(request: String, language: String): SearchListMovieDTO =
        movieRepo.getSearchListMovie(request, language = language)

    override suspend fun getMovieDescription(movieId: Int, language: String): DescriptionMovieDTO =
        movieRepo.getDescriptionMovie(movieId, language = language)

    override suspend fun getSeriesDescription(
        seriesId: Int,
        language: String
    ): DescriptionSeriesDTO =
        movieRepo.getDescriptionSeries(seriesId, language)

    override suspend fun getPersonDescription(
        personId: Int,
        language: String
    ): DescriptionPersonDTO =
        movieRepo.getDescriptionPerson(personId, language = language)

    override suspend fun getNowPlayingMovies(language: String): NowPlayingListMovieDTO =
        movieRepo.getNowPlayingMovies(language)

    override suspend fun getTrending(timeWindow: String, language: String): TrendingListDTO =
        movieRepo.getTrending(timeWindow, language)

    override suspend fun getDiscoverTV(language: String): DiscoverListTVDTO =
        movieRepo.getDiscoverTV(language = language)

    override suspend fun getDiscoverMovie(language: String): DiscoverListMovieDTO =
        movieRepo.getDiscoverMovie(language = language)
}

