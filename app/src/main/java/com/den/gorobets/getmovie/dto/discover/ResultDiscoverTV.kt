package com.den.gorobets.getmovie.dto.discover

import androidx.annotation.Keep
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@Keep
data class ResultDiscoverTV(

    @SerialName("backdrop_path")
    val backdropPath: String? = null,

    @SerialName("first_air_date")
    val firstAirDate: String? = null,

    @SerialName("genre_ids")
    val genreIDS: List<Int>? = null,

    val id: Int,
    val name: String? = null,

    @SerialName("origin_country")
    val originCountry: List<String>? = null,

    @SerialName("original_language")
    val originalLanguage: String? = null,

    @SerialName("original_name")
    val originalName: String? = null,

    val overview: String? = null,
    val popularity: Double? = null,

    @SerialName("poster_path")
    val posterPath: String? = null,

    @SerialName("vote_average")
    val voteAverage: Double? = null,

    @SerialName("vote_count")
    val voteCount: Int
)
