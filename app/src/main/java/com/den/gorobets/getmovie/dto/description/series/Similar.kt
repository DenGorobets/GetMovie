package com.den.gorobets.getmovie.dto.description.series

import androidx.annotation.Keep
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@Keep
data class Similar(

    val page: Int,
    val results: List<SimilarResult>? = null,

    @SerialName("total_pages")
    val totalPages: Int,

    @SerialName("total_results")
    val totalResults: Int
)