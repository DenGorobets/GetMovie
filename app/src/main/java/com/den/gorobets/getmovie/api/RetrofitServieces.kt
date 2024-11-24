package com.den.gorobets.getmovie.api

import com.den.gorobets.getmovie.GetMovieApplication.Companion.DEFAULT_ADULT
import com.den.gorobets.getmovie.GetMovieApplication.Companion.DEFAULT_LANGUAGE
import com.den.gorobets.getmovie.GetMovieApplication.Companion.TIME_WINDOW
import com.den.gorobets.getmovie.dto.description.movie.DescriptionMovieDTO
import com.den.gorobets.getmovie.dto.description.series.DescriptionSeriesDTO
import com.den.gorobets.getmovie.dto.now_playing.NowPlayingListMovieDTO
import com.den.gorobets.getmovie.dto.search.TrendingListDTO
import com.den.gorobets.getmovie.dto.discover.DiscoverListMovieDTO
import com.den.gorobets.getmovie.dto.discover.DiscoverListTVDTO
import com.den.gorobets.getmovie.dto.person.DescriptionPersonDTO
import com.den.gorobets.getmovie.dto.search.SearchListMovieDTO
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query

const val APPEND_TO_RESPONSE: String = "append_to_response"
const val APPEND_STRING: String = "videos,images,credits,similar,translations"
const val ADULT_INCLUDE: String = "include_adult"
const val LANGUAGE_AND_REGION: String = "language"
const val CURRENT_PAGE: String = "page"
const val SORT_BY: String = "sort_by"
const val SORT_TYPE: String =
    "popularity.desc" //popularity.asc, revenue.*, primary_release_date.*, vote_average.*, vote_count.*,
const val AUTHORIZATION: String = "Authorization"

interface RetrofitServices {

    @GET("search/multi")
    suspend fun getSearchListMovie(
        @Query("query") query: String,
        @Query(ADULT_INCLUDE) includeAdult: Boolean = DEFAULT_ADULT,
        @Query("primary_release_year") releaseYear: String = "", //set year
        @Query(LANGUAGE_AND_REGION) language: String = DEFAULT_LANGUAGE, //set region and language "en-US"
        @Query(CURRENT_PAGE) page: Int = 1,
        @Header(AUTHORIZATION) authorization: String = TMBD_API_KEY
    ): Response<SearchListMovieDTO>

    @GET("movie/{movie_id}")
    suspend fun getDescriptionMovie(
        @Path("movie_id") movieId: Int,
        @Query(ADULT_INCLUDE) includeAdult: Boolean = DEFAULT_ADULT,
        @Query(APPEND_TO_RESPONSE) appendToResponse: String = APPEND_STRING,
        @Query(LANGUAGE_AND_REGION) language: String = DEFAULT_LANGUAGE, //set region and language "en-US"
        @Header(AUTHORIZATION) authorization: String = TMBD_API_KEY
    ): Response<DescriptionMovieDTO>

    @GET("tv/{series_id}")
    suspend fun getDescriptionSeries(
        @Path("series_id") seriesId: Int,
        @Query(APPEND_TO_RESPONSE) appendToResponse: String = APPEND_STRING,
        @Query(LANGUAGE_AND_REGION) language: String = DEFAULT_LANGUAGE, //set region and language "en-US"
        @Header(AUTHORIZATION) authorization: String = TMBD_API_KEY
    ): Response<DescriptionSeriesDTO>

    @GET("person/{person_id}")
    suspend fun getDescriptionPerson(
        @Path("person_id") personId: Int,
        @Query(ADULT_INCLUDE) includeAdult: Boolean = DEFAULT_ADULT,
        @Query(APPEND_TO_RESPONSE) appendToResponse: String = "movie_credits",
        @Query(LANGUAGE_AND_REGION) language: String = DEFAULT_LANGUAGE, //set region and language "en-US"
        @Header(AUTHORIZATION) authorization: String = TMBD_API_KEY
    ): Response<DescriptionPersonDTO>

    @GET("discover/movie")
    suspend fun getDiscoverMovie(
        @Query(ADULT_INCLUDE) includeAdult: Boolean = DEFAULT_ADULT,
        @Query(LANGUAGE_AND_REGION) language: String = DEFAULT_LANGUAGE, //set region and language "en-US"
        @Query(SORT_BY) sortBy: String = "popularity.desc",
        @Query(CURRENT_PAGE) page: Int = 1,
        @Header(AUTHORIZATION) authorization: String = TMBD_API_KEY
    ): Response<DiscoverListMovieDTO>

    @GET("discover/tv")
    suspend fun getDiscoverTV(
        @Query(ADULT_INCLUDE) includeAdult: Boolean = DEFAULT_ADULT,
        @Query("include_null_first_air_dates") includeNullFirstAirDates: Boolean = false,
        @Query(LANGUAGE_AND_REGION) language: String = DEFAULT_LANGUAGE, //set region and language "en-US"
        @Query(SORT_BY) sortBy: String = SORT_TYPE,
        @Query(CURRENT_PAGE) page: Int = 1,
        @Header(AUTHORIZATION) authorization: String = TMBD_API_KEY
    ): Response<DiscoverListTVDTO>

    @GET("trending/all/{time_window}")
    suspend fun getTrending(
        @Path("time_window") timeWindow: String = TIME_WINDOW, //6h, day, week
        @Query(LANGUAGE_AND_REGION) language: String = DEFAULT_LANGUAGE, //set region and language "en-US"
        @Header(AUTHORIZATION) authorization: String = TMBD_API_KEY
    ): Response<TrendingListDTO>

    @GET("movie/now_playing")
    suspend fun getNowPlayingMovies(
        @Query(LANGUAGE_AND_REGION) language: String = DEFAULT_LANGUAGE, //set region and language "en-US"
        @Query(CURRENT_PAGE) page: Int = 1,
        @Header(AUTHORIZATION) authorization: String = TMBD_API_KEY
    ): Response<NowPlayingListMovieDTO>
}