package com.den.gorobets.getmovie.repo

import com.den.gorobets.getmovie.dto.description.movie.DescriptionMovieDTO
import com.den.gorobets.getmovie.dto.description.series.DescriptionSeriesDTO
import com.example.lesson1.data.pojo_tmdb.person.DescriptionPersonDTO
import com.example.lesson1.data.pojo_tmdb.search.SearchListMovieDTO

interface MainRepository {

    suspend fun getSearchedMovies(request: String, language: String): SearchListMovieDTO
    suspend fun getMovieDescription(movieId: Int, language: String): DescriptionMovieDTO
    suspend fun getSeriesDescription(seriesId: Int, language: String): DescriptionSeriesDTO
    suspend fun getPersonDescription(personId: Int, language: String): DescriptionPersonDTO
}