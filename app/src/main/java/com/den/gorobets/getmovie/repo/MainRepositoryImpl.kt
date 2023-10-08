package com.den.gorobets.getmovie.repo

import com.den.gorobets.getmovie.api.RetrofitServices

class MainRepositoryImpl(private val movieRepo: RetrofitServices) : MainRepository {

    override suspend fun getSearchedMovies(request: String, language: String) =
        movieRepo.getSearchListMovie(request, language = language)

    override suspend fun getMovieDescription(movieId: Int, language: String) =
        movieRepo.getDescriptionMovie(movieId, language = language)

    override suspend fun getSeriesDescription(seriesId: Int, language: String) =
        movieRepo.getDescriptionSeries(seriesId, language)

    override suspend fun getPersonDescription(personId: Int, language: String) =
        movieRepo.getDescriptionPerson(personId, language = language)
}

