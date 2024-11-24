package com.den.gorobets.getmovie.dto.now_playing

import androidx.annotation.Keep
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@Keep
data class NowPlayingListMovieDTO(

    val dates: Dates? = null,
    val page: Int? = null,

    @SerialName("results")
    val resultNowPlayingMovies: List<ResultNowPlayingMovie>? = null,

    @SerialName("total_pages")
    val totalPages: Int? = null,

    @SerialName("total_results")
    val totalResults: Int? = null
)