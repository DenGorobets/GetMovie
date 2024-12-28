package com.den.gorobets.getmovie.utils.data

import androidx.compose.runtime.Stable
import com.den.gorobets.getmovie.GetMovieApplication
import com.den.gorobets.getmovie.dto.description.Genre
import com.den.gorobets.getmovie.dto.description.ProductionCountry
import com.den.gorobets.getmovie.dto.description.ScrollerItemData
import com.den.gorobets.getmovie.dto.description.Videos
import com.den.gorobets.getmovie.dto.description.movie.Cast
import java.text.DecimalFormat

@Stable
data class SeriesBottomPartData(
    val numberOfEpisodes: Int,
    val numberOfSeasons: Int,
    val seriesStatus: String,
    val inProduction: Boolean,
    val episodeRuntime: List<Int>,
    val firstAirDate: String,
    val lastAirDate: String,
    val nextEpisodeToAir: String,
    val lastEpisodeToAir: String,
    val voteAverage: Double?,
    val genres: List<Genre>,
    val productionCountries: List<ProductionCountry>,
    val crew: List<Cast>,
    val similar: List<ScrollerItemData>,
    val seasonsList: List<ScrollerItemData>,
    val overview: String,
    val homepage: String,
    val videoPath: Videos?
) {

    val actorsCast: Map<String, Int>
        get() {
            val actorsMap = mutableMapOf<String, Int>()

            crew.map { actors ->
                actorsMap[actors.name.orEmpty()] = actors.id
            }

            return actorsMap
        }

    val producersCast: Map<String, Int>
        get() {
            val producersMap = mutableMapOf<String, Int>()

            crew.map { producer ->
                if (producer.job == "Producer") {
                    producersMap[producer.name.orEmpty()] = producer.id
                }
            }

            return producersMap
        }

    val directorsCast: Map<String, Int>
        get() {
            val directorsMap = mutableMapOf<String, Int>()

            crew.map { director ->
                if (director.job == "Director") {
                    directorsMap[director.name.orEmpty()] = director.id
                }
            }

            return directorsMap
        }

    val filmingCountry: List<String>
        get() {
            val listCountry = mutableListOf<String>()

            productionCountries.map { country ->
                listCountry.add(country.name.orEmpty())
            }

            return listCountry
        }

    val filmGenre: List<String>
        get() {
            val listGenre = mutableListOf<String>()

            genres.map { genre ->
                listGenre.add(genre.name.orEmpty())
            }

            return listGenre
        }

    val trailerVideo: String
        get() {

            val listVideoId = mutableListOf<String>()

            videoPath?.results?.map { videoId ->
                if (videoId.type == "Trailer") listVideoId.add(videoId.key.orEmpty())
            }

            return if (listVideoId.isNotEmpty()) listVideoId[0] else ""
        }

    val correctAvrRuntime: String
        get() {
            val allSeriesRuntime = episodeRuntime.average().toInt()
            val hours = allSeriesRuntime / 60
            val remainingMinutes = allSeriesRuntime % 60

            return if (hours != 0 && remainingMinutes != 0) {
                when (GetMovieApplication.DEFAULT_LANGUAGE) {
                    "uk" -> "${hours}г. ${remainingMinutes}хв."
                    else -> "$hours:$remainingMinutes min"
                }
            } else {
                ""
            }
        }

    val formatVote: String
        get() {
            val formatedVote = DecimalFormat("#.#").format(voteAverage).toString()

            return if (formatedVote != "0") formatedVote else ""
        }
}