package com.example.lesson1.data.pojo_tmdb.person

import androidx.annotation.Keep
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@Keep
data class DescriptionPersonDTO(

    val adult: Boolean,

    @SerialName("also_known_as")
    val alsoKnownAs: List<String>? = null,

    val biography: String? = null,
    val birthday: String? = null,
    val deathday: String? = null,
    val gender: Int,
    val homepage: String? = null,
    val id: Int,

    @SerialName("imdb_id")
    val imdbID: String? = null,

    @SerialName("known_for_department")
    val knownForDepartment: String? = null,

    val name: String? = null,

    @SerialName("place_of_birth")
    val placeOfBirth: String? = null,

    val popularity: Double,

    @SerialName("profile_path")
    val profilePath: String? = null,

    @SerialName("movie_credits")
    val movieCredits: MovieCredits? = null
)

enum class OriginalLanguage(val value: String) {

    En("en"),
    Es("es");

    companion object {
        fun fromValue(value: String): OriginalLanguage = when (value) {
            "en" -> En
            "es" -> Es
            else -> throw IllegalArgumentException()
        }
    }
}
