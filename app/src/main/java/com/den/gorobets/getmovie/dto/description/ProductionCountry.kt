package com.den.gorobets.getmovie.dto.description

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ProductionCountry(

    @SerialName("iso_3166_1")
    val isoCountry: String? = null,

    val name: String? = null
)
