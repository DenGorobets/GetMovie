package com.den.gorobets.getmovie.dto.search

import androidx.annotation.Keep
import com.den.gorobets.getmovie.dto.search.KnownFor
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@Keep
data class Result(
    val adult: Boolean,

    @SerialName("backdrop_path")
    val backdropPath: String? = null,

    val id: Int,
    val title: String? = null,

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

    val name: String? = null,

    @SerialName("original_name")
    val originalName: String? = null,

    @SerialName("first_air_date")
    val firstAirDate: String? = null,

    @SerialName("profile_path")
    val profilePath: String? = null,

    @SerialName("known_for_department")
    val knownForDepartment: String? = null,

    @SerialName("origin_country")
    val originCountry: List<String>? = null,

    @SerialName("known_for")
    val knownFor: List<KnownFor>? = null
)

