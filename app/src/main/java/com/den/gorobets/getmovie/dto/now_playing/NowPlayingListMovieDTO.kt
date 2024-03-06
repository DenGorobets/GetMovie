package com.example.lesson1.data.pojo_tmdb.now_playing

import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NowPlayingListMovieDTO @OptIn(ExperimentalSerializationApi::class) constructor(

    val dates: Dates,
    val page: Int,
    val resultNowPlayingMovies: List<ResultNowPlayingMovie>,

    @SerialName("total_pages")
    val totalPages: Int,

    @SerialName("total_results")
    val totalResults: Int
)