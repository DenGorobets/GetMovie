package com.den.gorobets.getmovie.dto.search

import androidx.annotation.Keep
import com.den.gorobets.getmovie.dto.search.Result
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@Keep
data class SearchListMovieDTO(

    val page: Int,
    val results: List<Result>,

    @SerialName("total_pages")
    val totalPages: Int,

    @SerialName("total_results")
    val totalResults: Int
)