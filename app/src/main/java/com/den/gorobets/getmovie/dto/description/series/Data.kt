package com.den.gorobets.getmovie.dto.description.series

import androidx.annotation.Keep
import kotlinx.serialization.Serializable

@Serializable
@Keep
data class Data(

    val name: String? = null,
    val overview: String? = null,
    val homepage: String? = null,
    val tagline: String? = null
)