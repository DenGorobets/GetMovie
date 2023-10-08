package com.den.gorobets.getmovie.dto.description

import com.den.gorobets.getmovie.dto.description.movie.Backdrop
import kotlinx.serialization.Serializable

@Serializable
data class Images(

    val backdrops: List<Backdrop>? = null,
    val logos: List<Backdrop>? = null,
    val posters: List<Backdrop>? = null
)
