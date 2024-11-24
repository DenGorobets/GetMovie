package com.den.gorobets.getmovie.dto.search

import androidx.annotation.Keep
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@Keep
data class KnownFor(

    val adult: Boolean,

    @SerialName("backdrop_path")
    val backdropPath: String? = null,

    val id: Int,
    val title: String? = null,

    @SerialName("original_language")
    val originalLanguage: String,

    @SerialName("original_title")
    val originalTitle: String? = null,

    val overview: String? = null,

    @SerialName("poster_path")
    val posterPath: String? = null,

    @SerialName("media_type")
    val mediaType: String? = null,

    @SerialName("genre_ids")
    val genreIDS: List<Int>? = null,

    val popularity: Double,

    @SerialName("release_date")
    val releaseDate: String? = null,

    val video: Boolean? = null,

    @SerialName("vote_average")
    val voteAverage: Double,

    @SerialName("vote_count")
    val voteCount: Int,

    val name: String? = null,

    @SerialName("original_name")
    val originalName: String? = null,

    @SerialName("first_air_date")
    val firstAirDate: String? = null,

    @SerialName("origin_country")
    val originCountry: List<String>? = null
)