package com.den.gorobets.getmovie.dto.description

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SpokenLanguage(

    @SerialName("english_name")
    val englishName: String? = null,

    @SerialName("iso_639_1")
    val isoCountry: String? = null,

    val name: String? = null
)