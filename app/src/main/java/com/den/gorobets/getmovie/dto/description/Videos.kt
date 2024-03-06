package com.den.gorobets.getmovie.dto.description

import kotlinx.serialization.Serializable

@Serializable
data class Videos(

    val results: List<VideosResult>? = null
)