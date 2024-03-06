package com.example.lesson1.data.pojo_tmdb.discover

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DiscoverListMovieDTO(

    val page: Int,
    val results: List<ResultDiscoverMovie>,

    @SerialName("total_pages")
    val totalPages: Int,

    @SerialName("total_results")
    val totalResults: Int
)