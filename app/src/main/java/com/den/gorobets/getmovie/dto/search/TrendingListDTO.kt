package com.example.lesson1.data.pojo_tmdb.search

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TrendingListDTO(
    val page: Int,
    val results: List<Result>,

    @SerialName("total_pages")
    val totalPages: Int,

    @SerialName("total_results")
    val totalResults: Int
)

enum class MediaType(val value: String) {
    Movie("Movie"),
    Person("Person"),
    Tv("Series");

    companion object {
        fun fromValue(value: String): MediaType = when (value) {
            "movie" -> Movie
            "person" -> Person
            "tv" -> Tv
            else -> throw IllegalArgumentException()
        }
    }
}
