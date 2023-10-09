package com.example.lesson1.data.pojo_tmdb.person

import androidx.annotation.Keep
import kotlinx.serialization.Serializable

@Serializable
@Keep
data class MovieCredits(

    val cast: List<Cast>? = null,
    val crew: List<Cast>? = null
)
