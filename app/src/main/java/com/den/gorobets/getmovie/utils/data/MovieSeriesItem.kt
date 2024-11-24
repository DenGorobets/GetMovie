package com.den.gorobets.getmovie.utils.data

import androidx.compose.runtime.Stable
import java.util.UUID

@Stable
data class MovieSeriesItem(
    val poster: String,
    val title: String,
    val imdbId: Int,
    val isTVSeries: Boolean = false,
    val randomId: String = UUID.randomUUID().toString()
)