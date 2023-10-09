package com.den.gorobets.getmovie.dto.search

import androidx.annotation.Keep
import com.example.lesson1.data.pojo_tmdb.search.Result
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@Keep
data class TrendingListDTO(

    val page: Int,
    val results: List<Result>? = null,

    @SerialName("total_pages")
    val totalPages: Int,

    @SerialName("total_results")
    val totalResults: Int
)