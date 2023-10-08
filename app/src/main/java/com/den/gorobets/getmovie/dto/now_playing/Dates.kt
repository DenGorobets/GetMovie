package com.example.lesson1.data.pojo_tmdb.now_playing

import kotlinx.serialization.Serializable

@Serializable
data class Dates(

    val maximum: String,
    val minimum: String
)
