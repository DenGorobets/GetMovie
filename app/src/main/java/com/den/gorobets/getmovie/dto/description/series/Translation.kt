package com.den.gorobets.getmovie.dto.description.series

import androidx.annotation.Keep
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@Keep
data class Translation(
    @SerialName("iso_3166_1")
    val iso3166_1: String? = null,

    @SerialName("iso_639_1")
    val iso639_1: String? = null,

    val name: String? = null,

    @SerialName("english_name")
    val englishName: String? = null,

    val data: Data? = null
)
