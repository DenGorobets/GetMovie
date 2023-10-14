package com.den.gorobets.getmovie.dto.description

import androidx.annotation.Keep
import kotlinx.serialization.Serializable

@Serializable
@Keep
data class Genre(

    val id: Int,
    val name: String? = null
)