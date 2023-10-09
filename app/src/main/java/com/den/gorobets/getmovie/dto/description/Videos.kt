package com.den.gorobets.getmovie.dto.description

import androidx.annotation.Keep
import kotlinx.serialization.Serializable

@Serializable
@Keep
data class Videos(

    val results: List<VideosResult>? = null
)