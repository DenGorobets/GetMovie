package com.den.gorobets.getmovie.dto.description

import androidx.annotation.Keep
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@Keep
data class ProductionCountry(

    @SerialName("iso_3166_1")
    val isoCountry: String? = null,

    val name: String? = null
)
