package com.den.gorobets.getmovie.dto.description.series

import androidx.annotation.Keep
import com.den.gorobets.getmovie.dto.description.Credits
import com.den.gorobets.getmovie.dto.description.Genre
import com.den.gorobets.getmovie.dto.description.Images
import com.den.gorobets.getmovie.dto.description.ProductionCompany
import com.den.gorobets.getmovie.dto.description.ProductionCountry
import com.den.gorobets.getmovie.dto.description.SpokenLanguage
import com.den.gorobets.getmovie.dto.description.Videos
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@Keep
data class DescriptionSeriesDTO(

    val adult: Boolean,

    @SerialName("backdrop_path")
    val backdropPath: String? = null,

    @SerialName("created_by")
    val createdBy: List<CreatedBy>? = null,

    @SerialName("episode_run_time")
    val episodeRunTime: List<Int>? = null,

    @SerialName("first_air_date")
    val firstAirDate: String? = null,

    val genres: List<Genre>,
    val homepage: String? = null,
    val id: Int,

    @SerialName("in_production")
    val inProduction: Boolean? = null,

    val languages: List<String>? = null,

    @SerialName("last_air_date")
    val lastAirDate: String? = null,

    @SerialName("last_episode_to_air")
    val lastEpisodeToAir: TEpisodeToAir? = null,

    val name: String? = null,

    @SerialName("next_episode_to_air")
    val nextEpisodeToAir: TEpisodeToAir? = null,

    val networks: List<Network>? = null,

    @SerialName("number_of_episodes")
    val numberOfEpisodes: Int,

    @SerialName("number_of_seasons")
    val numberOfSeasons: Int,

    @SerialName("origin_country")
    val originCountry: List<String>? = null,

    @SerialName("original_language")
    val originalLanguage: String? = null,

    @SerialName("original_name")
    val originalName: String? = null,

    val overview: String? = null,
    val popularity: Double,

    @SerialName("poster_path")
    val posterPath: String? = null,

    @SerialName("production_companies")
    val productionCompanies: List<ProductionCompany>? = null,

    @SerialName("production_countries")
    val productionCountries: List<ProductionCountry>? = null,

    val seasons: List<Season>? = null,

    @SerialName("spoken_languages")
    val spokenLanguages: List<SpokenLanguage>? = null,

    val status: String? = null,
    val tagline: String? = null,
    val type: String? = null,

    @SerialName("vote_average")
    val voteAverage: Double,

    @SerialName("vote_count")
    val voteCount: Int,

    val videos: Videos? = null,
    val images: Images? = null,
    val credits: Credits? = null,
    val similar: Similar? = null,
    val translations: Translations? = null
)