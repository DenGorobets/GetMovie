package com.den.gorobets.getmovie.extensions

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.den.gorobets.getmovie.R
import com.example.lesson1.data.pojo_tmdb.search.Result

fun Result.selectTitleResource(): String? =

    when (mediaType) {
        "movie" -> title
        "person" -> name
        "tv" -> name
        else -> title
    }

fun Result.selectImageResource(): String? =

    when (mediaType) {
        "movie" -> posterPath
        "person" -> profilePath
        "tv" -> posterPath
        else -> posterPath
    }

fun Result.selectYearResource(): String =

    when (mediaType) {
        "movie" -> releaseDate?.take(4) ?: ""
        "person" -> ""
        "tv" -> knownFor?.get(0)?.firstAirDate?.take(4) ?: ""
        else -> ""
    }

@Composable
fun Result.selectTypeResource(): String =

    when (mediaType) {
        "movie" -> stringResource(R.string.text_movie)
        "tv" -> stringResource(R.string.text_series)
        "person" ->
            when (knownForDepartment) {
                "Acting" -> stringResource(R.string.text_actor)
                "Sound" -> stringResource(R.string.text_sound_master)
                "Directing" -> stringResource(R.string.text_director)
                "Producer" -> stringResource(R.string.text_producer)
                else -> ""
            }

        else -> ""
    }

fun Pair<String, String>.displayAdditionalData(): String =

    when {
        first == "" -> second
        second == "" -> first
        else -> "${first}, $second"
    }
