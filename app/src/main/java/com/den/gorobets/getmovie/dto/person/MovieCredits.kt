package com.example.lesson1.data.pojo_tmdb.person

import kotlinx.serialization.Serializable

@Serializable
data class MovieCredits(

    val cast: List<Cast>? = null,
    val crew: List<Cast>? = null
)
