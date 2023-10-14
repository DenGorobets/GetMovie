package com.den.gorobets.getmovie.dto.description

import androidx.annotation.Keep
import com.den.gorobets.getmovie.dto.description.movie.Cast
import kotlinx.serialization.Serializable

@Serializable
@Keep
data class Credits(
    val cast: List<Cast>? = null,
    val crew: List<Cast>? = null
)
