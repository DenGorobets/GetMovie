package com.den.gorobets.getmovie.extensions

import com.den.gorobets.getmovie.dto.description.ScrollerItemData
import com.den.gorobets.getmovie.dto.description.series.SerialSeasonItem
import com.den.gorobets.getmovie.utils.data.MovieSeriesPersonItem

fun List<MovieSeriesPersonItem>.toMovieScrollerData(): List<ScrollerItemData> =
    map { ScrollerItemData.MovieSeries(it) }

fun List<SerialSeasonItem>.toSeasonScrollerData(): List<ScrollerItemData> =
    map { ScrollerItemData.Season(it) }
