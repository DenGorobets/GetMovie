package com.den.gorobets.getmovie.dto.person

import androidx.annotation.Keep
import com.den.gorobets.getmovie.dto.person.Cast
import kotlinx.serialization.Serializable

@Serializable
@Keep
data class MovieCredits(

    val cast: List<Cast>? = null,
    val crew: List<Cast>? = null
)
