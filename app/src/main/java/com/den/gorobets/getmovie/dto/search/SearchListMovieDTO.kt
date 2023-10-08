package com.example.lesson1.data.pojo_tmdb.search

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SearchListMovieDTO(

    val page: Int,
    val results: List<Result>,

    @SerialName("total_pages")
    val totalPages: Int,

    @SerialName("total_results")
    val totalResults: Int
)