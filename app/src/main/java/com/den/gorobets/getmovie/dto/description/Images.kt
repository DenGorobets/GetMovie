package com.den.gorobets.getmovie.dto.description

import androidx.annotation.Keep
import com.den.gorobets.getmovie.dto.description.movie.Backdrop
import kotlinx.serialization.Serializable

@Serializable
@Keep
data class Images(

    val backdrops: List<Backdrop>? = null,
    val logos: List<Backdrop>? = null,
    val posters: List<Backdrop>? = null
)
