package com.den.gorobets.getmovie.dto.description

import com.den.gorobets.getmovie.dto.description.series.SerialSeasonItem
import com.den.gorobets.getmovie.utils.data.MovieSeriesPersonItem

sealed class ScrollerItemData {
    data class MovieSeries(
        val item: MovieSeriesPersonItem
    ) : ScrollerItemData()

    data class Season(
        val item: SerialSeasonItem
    ) : ScrollerItemData()
}