package com.den.gorobets.getmovie.dto.description

import androidx.annotation.Keep
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@Keep
data class VideosResult(

    @SerialName("iso_639_1")
    val isoLanguage: String? = null,

    @SerialName("iso_3166_1")
    val isoCountry: String? = null,

    val name: String? = null,
    val key: String? = null,

    @SerialName("published_at")
    val publishedAt: String? = null,

    val site: String? = null,
    val size: Int,
    val type: String? = null,
    val official: Boolean,
    val id: String? = null
)
