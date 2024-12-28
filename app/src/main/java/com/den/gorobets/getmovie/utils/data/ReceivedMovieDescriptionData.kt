package com.den.gorobets.getmovie.utils.data

import androidx.compose.runtime.Stable

@Stable
data class ReceivedMovieDescriptionData(
    val topPartData: DescriptionTopPartData,
    val bottomPartData: MovieBottomPartData
)

@Stable
data class ReceivedSeriesDescriptionData(
    val topPartData: DescriptionTopPartData,
    val bottomPartData: SeriesBottomPartData
)