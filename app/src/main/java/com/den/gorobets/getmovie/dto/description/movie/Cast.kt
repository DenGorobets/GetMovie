package com.den.gorobets.getmovie.dto.description.movie

import androidx.annotation.Keep
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@Keep
data class Cast(
    val adult: Boolean,
    val gender: Int,
    val id: Int,

    @SerialName("known_for_department")
    val knownForDepartment: String? = null,

    val name: String? = null,

    @SerialName("original_name")
    val originalName: String,

    val popularity: Double,

    @SerialName("profile_path")
    val profilePath: String? = null,

    @SerialName("cast_id")
    val castID: Int? = null,

    val character: String? = null,

    @SerialName("credit_id")
    val creditID: String? = null,

    val order: Int? = null,
    val department: String? = null,
    val job: String? = null
)