package com.example.lesson1.data.pojo_tmdb.person

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Cast(

    val adult: Boolean,

    @SerialName("backdrop_path")
    val backdropPath: String? = null,

    @SerialName("genre_ids")
    val genreIDS: List<Int>? = null,

    val id: Int,

    @SerialName("original_language")
    val originalLanguage: OriginalLanguage? = null,

    @SerialName("original_title")
    val originalTitle: String? = null,

    val overview: String? = null,
    val popularity: Double,

    @SerialName("poster_path")
    val posterPath: String? = null,

    @SerialName("release_date")
    val releaseDate: String? = null,

    val title: String? = null,
    val video: Boolean,

    @SerialName("vote_average")
    val voteAverage: Double,

    @SerialName("vote_count")
    val voteCount: Int,

    val character: String? = null,

    @SerialName("credit_id")
    val creditID: String? = null,

    val order: Int? = null,
    val department: String? = null,
    val job: String? = null
)