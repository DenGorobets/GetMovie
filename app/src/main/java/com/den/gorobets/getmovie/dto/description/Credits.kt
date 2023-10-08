package com.den.gorobets.getmovie.dto.description

import com.den.gorobets.getmovie.dto.description.movie.Cast
import kotlinx.serialization.Serializable

@Serializable
data class Credits(
    val cast: List<Cast>? = null,
    val crew: List<Cast>? = null
)
