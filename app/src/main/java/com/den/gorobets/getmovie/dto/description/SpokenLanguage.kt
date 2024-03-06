package com.den.gorobets.getmovie.dto.description

import androidx.annotation.Keep
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@Keep
data class SpokenLanguage(

    @SerialName("english_name")
    val englishName: String? = null,

    @SerialName("iso_639_1")
    val isoCountry: String? = null,

    val name: String? = null
)