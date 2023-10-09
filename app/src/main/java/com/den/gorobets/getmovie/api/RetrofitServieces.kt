package com.den.gorobets.getmovie.api

import com.den.gorobets.getmovie.dto.description.movie.DescriptionMovieDTO
import com.den.gorobets.getmovie.dto.description.series.DescriptionSeriesDTO
import com.den.gorobets.getmovie.dto.now_playing.NowPlayingListMovieDTO
import com.den.gorobets.getmovie.dto.search.TrendingListDTO
import com.example.lesson1.data.pojo_tmdb.discover.DiscoverListMovieDTO
import com.example.lesson1.data.pojo_tmdb.discover.DiscoverListTVDTO
import com.example.lesson1.data.pojo_tmdb.person.DescriptionPersonDTO
import com.example.lesson1.data.pojo_tmdb.search.SearchListMovieDTO
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query

const val APPEND_TO_RESPONSE = "append_to_response"
const val APPEND_STRING = "videos,images,credits,similar,translations"
const val ADULT_INCLUDE = "include_adult"
const val LANGUAGE_AND_REGION = "language"
const val CURRENT_PAGE = "page"
const val SORT_BY = "sort_by"
const val SORT_TYPE =
    "popularity.desc" //popularity.asc, revenue.*, primary_release_date.*, vote_average.*, vote_count.*,
const val AUTHORIZATION = "Authorization"

interface RetrofitServices {

    @GET("search/multi")
    suspend fun getSearchListMovie(
        @Query("query") query: String,
        @Query(ADULT_INCLUDE) includeAdult: Boolean = false,
        @Query("primary_release_year") releaseYear: String = "", //set year
        @Query(LANGUAGE_AND_REGION) language: String, //set region and language "en-US"
        @Query(CURRENT_PAGE) page: Int = 1,
        @Header(AUTHORIZATION) authorization: String = TMBD_API_KEY
    ): SearchListMovieDTO

    @GET("movie/{movie_id}")
    suspend fun getDescriptionMovie(
        @Path("movie_id") movieId: Int,
        @Query(ADULT_INCLUDE) includeAdult: Boolean = false,
        @Query(APPEND_TO_RESPONSE) appendToResponse: String = APPEND_STRING,
        @Query(LANGUAGE_AND_REGION) language: String, //set region and language "en-US"
        @Header(AUTHORIZATION) authorization: String = TMBD_API_KEY
    ): DescriptionMovieDTO

    @GET("tv/{series_id}")
    suspend fun getDescriptionSeries(
        @Path("series_id") seriesId: Int,
        @Query(APPEND_TO_RESPONSE) appendToResponse: String = APPEND_STRING,
        @Query(LANGUAGE_AND_REGION) language: String = "", //set region and language "en-US"
        @Header(AUTHORIZATION) authorization: String = TMBD_API_KEY
    ): DescriptionSeriesDTO

    @GET("person/{person_id}")
    suspend fun getDescriptionPerson(
        @Path("person_id") personId: Int,
        @Query(ADULT_INCLUDE) includeAdult: Boolean = false,
        @Query(APPEND_TO_RESPONSE) appendToResponse: String = "movie_credits",
        @Query(LANGUAGE_AND_REGION) language: String, //set region and language "en-US"
        @Header(AUTHORIZATION) authorization: String = TMBD_API_KEY
    ): DescriptionPersonDTO

    @GET("discover/movie")
    suspend fun getDiscoverMovie(
        @Query(ADULT_INCLUDE) includeAdult: Boolean = false,
        @Query(LANGUAGE_AND_REGION) language: String = "", //set region and language "en-US"
        @Query(SORT_BY) sortBy: String = "popularity.desc",
        @Query(CURRENT_PAGE) page: Int = 1,
        @Header(AUTHORIZATION) authorization: String = TMBD_API_KEY
    ): DiscoverListMovieDTO

    @GET("discover/tv")
    suspend fun getDiscoverTV(
        @Query(ADULT_INCLUDE) includeAdult: Boolean = false,
        @Query("include_null_first_air_dates") includeNullFirstAirDates: Boolean = false,
        @Query(LANGUAGE_AND_REGION) language: String = "", //set region and language "en-US"
        @Query(SORT_BY) sortBy: String = SORT_TYPE,
        @Query(CURRENT_PAGE) page: Int = 1,
        @Header(AUTHORIZATION) authorization: String = TMBD_API_KEY
    ): DiscoverListTVDTO

    @GET("trending/all/{time_window}")
    suspend fun getTrending(
        @Path("time_window") timeWindow: String = "week", //6h, day, week
        @Query(LANGUAGE_AND_REGION) language: String, //set region and language "en-US"
        @Header(AUTHORIZATION) authorization: String = TMBD_API_KEY
    ): TrendingListDTO

    @GET("movie/now_playing")
    suspend fun getNowPlayingMovies(
        @Query(LANGUAGE_AND_REGION) language: String, //set region and language "en-US"
        @Query(CURRENT_PAGE) page: Int = 1,
        @Header(AUTHORIZATION) authorization: String = TMBD_API_KEY
    ): NowPlayingListMovieDTO
}