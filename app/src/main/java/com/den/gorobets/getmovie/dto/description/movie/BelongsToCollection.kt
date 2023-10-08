package com.den.gorobets.getmovie.dto.description.movie

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BelongsToCollection(

    val id: Int,
    val name: String? = null,

    @SerialName("poster_path")
    val posterPath: String? = null,

    @SerialName("backdrop_path")
    val backdropPath: String? = null
)