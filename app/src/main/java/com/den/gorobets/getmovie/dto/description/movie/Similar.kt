package com.den.gorobets.getmovie.dto.description.movie

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Similar(

    val page: Int,
    val results: List<SimilarResult>? = null,

    @SerialName("total_pages")
    val totalPages: Int,

    @SerialName("total_results")
    val totalResults: Int
)