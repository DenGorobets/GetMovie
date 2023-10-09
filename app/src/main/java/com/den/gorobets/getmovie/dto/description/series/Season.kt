package com.den.gorobets.getmovie.dto.description.series

import androidx.annotation.Keep
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@Keep
data class Season(

    @SerialName("air_date")
    val airDate: String? = null,

    @SerialName("episode_count")
    val episodeCount: Int,

    val id: Int,
    val name: String? = null,
    val overview: String? = null,

    @SerialName("poster_path")
    val posterPath: String? = null,

    @SerialName("season_number")
    val seasonNumber: Int
)
