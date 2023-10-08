package com.example.lesson1.data.pojo_tmdb.discover

import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DiscoverListTVDTO @OptIn(ExperimentalSerializationApi::class) constructor(

    val page: Int,
    val results: List<ResultDiscoverTV>,

    @SerialName("total_pages")
    val totalPages: Int,

    @SerialName("total_results")
    val totalResults: Int
)