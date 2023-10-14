package com.den.gorobets.getmovie.dto.now_playing

import androidx.annotation.Keep
import com.example.lesson1.data.pojo_tmdb.now_playing.Dates
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