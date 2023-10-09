package com.example.lesson1.data.pojo_tmdb.discover

import androidx.annotation.Keep
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@Keep
data class DiscoverListMovieDTO(

    val page: Int,
    val results: List<ResultDiscoverMovie>? = null,

    @SerialName("total_pages")
    val totalPages: Int,

    @SerialName("total_results")
    val totalResults: Int
)