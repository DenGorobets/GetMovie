package com.example.lesson1.data.pojo_tmdb.now_playing

import androidx.annotation.Keep
import kotlinx.serialization.Serializable

@Serializable
@Keep
data class Dates(

    val maximum: String,
    val minimum: String
)
