package com.den.gorobets.getmovie.dto.description

import kotlinx.serialization.Serializable

@Serializable
data class Genre(

    val id: Int,
    val name: String? = null
)