package com.den.gorobets.getmovie.dto.description.movie

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Backdrop(

    @SerialName("aspect_ratio")
    val aspectRatio: Double,

    val height: Int,

    @SerialName("iso_639_1")
    val languageInISO: String? = null,

    @SerialName("file_path")
    val filePath: String? = null,

    @SerialName("vote_average")
    val voteAverage: Double,

    @SerialName("vote_count")
    val voteCount: Int,

    val width: Int
)