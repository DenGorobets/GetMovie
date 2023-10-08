package com.den.gorobets.getmovie.dto.description.series

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TEpisodeToAir(

    val id: Int,
    val name: String? = null,
    val overview: String? = null,

    @SerialName("vote_average")
    val voteAverage: Double,

    @SerialName("vote_count")
    val voteCount: Int,

    @SerialName("air_date")
    val airDate: String? = null,

    @SerialName("episode_number")
    val episodeNumber: Int,

    @SerialName("production_code")
    val productionCode: String? = null,

    val runtime: Int? = null,

    @SerialName("season_number")
    val seasonNumber: Int,

    @SerialName("show_id")
    val showID: Int,

    @SerialName("still_path")
    val stillPath: String? = null
)