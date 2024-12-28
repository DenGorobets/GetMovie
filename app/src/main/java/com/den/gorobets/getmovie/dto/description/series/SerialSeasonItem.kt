package com.den.gorobets.getmovie.dto.description.series

import androidx.compose.runtime.Stable
import java.util.UUID

@Stable
data class SerialSeasonItem(
    val imdbId: Int,
    val randomId: String = UUID.randomUUID().toString(),
    val airDate: String? = null,
    val episodeCount: Int,
    val name: String? = null,
    val overview: String? = null,
    val posterPath: String? = null,
    val seasonNumber: Int
)
