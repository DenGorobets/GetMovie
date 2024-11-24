package com.den.gorobets.getmovie.dto.now_playing

import androidx.annotation.Keep
import kotlinx.serialization.Serializable

@Serializable
@Keep
data class Dates(

    val maximum: String,
    val minimum: String
)
