package com.den.gorobets.getmovie.dto.description.series

import androidx.annotation.Keep
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@Keep
data class Network(

    val id: Int,

    @SerialName("logo_path")
    val logoPath: String? = null,

    val name: String? = null,

    @SerialName("origin_country")
    val originCountry: String? = null
)