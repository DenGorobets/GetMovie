package com.den.gorobets.getmovie.dto.description.movie

import androidx.annotation.Keep
import com.den.gorobets.getmovie.dto.description.Credits
import com.den.gorobets.getmovie.dto.description.Genre
import com.den.gorobets.getmovie.dto.description.Images
import com.den.gorobets.getmovie.dto.description.ProductionCompany
import com.den.gorobets.getmovie.dto.description.ProductionCountry
import com.den.gorobets.getmovie.dto.description.SpokenLanguage
import com.den.gorobets.getmovie.dto.description.Videos
import com.den.gorobets.getmovie.dto.description.series.Translations
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@Keep
data class DescriptionMovieDTO(

    @SerialName("belongs_to_collection")
    val belongsToCollection: BelongsToCollection? = null,
    val budget: Int,

    @SerialName("imdb_id")
    val imdbID: String? = null,

    @SerialName("original_language")
    val originalLanguage: OriginalLanguage? = null,

    @SerialName("original_title")
    val originalTitle: String? = null,

    val overview: String? = null,
    val popularity: Double,

    @SerialName("poster_path")
    val posterPath: String? = null,

    @SerialName("production_companies")
    val productionCompanies: List<ProductionCompany>? = null,

    @SerialName("production_countries")
    val productionCountries: List<ProductionCountry>? = null,

    @SerialName("release_date")
    val releaseDate: String? = null,

    val revenue: Int,
    val runtime: Int,

    @SerialName("spoken_languages")
    val spokenLanguages: List<SpokenLanguage>,

    val status: String? = null,
    val tagline: String? = null,
    val title: String? = null,
    val video: Boolean,

    @SerialName("vote_average")
    val voteAverage: Double,

    @SerialName("vote_count")
    val voteCount: Int,

    val videos: Videos? = null,
    val images: Images? = null,
    val credits: Credits? = null,
    val similar: Similar? = null,

    val adult: Boolean,

    @SerialName("backdrop_path")
    val backdropPath: String? = null,
    val genres: List<Genre>,
    val homepage: String? = null,
    val id: Int,

    @SerialName("in_production")
    val inProduction: Boolean? = null,

    val languages: List<String>? = null,
    val name: String? = null,

    @SerialName("origin_country")
    val originCountry: List<String>? = null,

    @SerialName("original_name")
    val originalName: String? = null,

    val type: String? = null,
    val translations: Translations? = null
)