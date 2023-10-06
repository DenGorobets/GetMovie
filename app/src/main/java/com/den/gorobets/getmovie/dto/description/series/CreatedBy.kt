package com.den.gorobets.getmovie.dto.description.series

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CreatedBy(

    val id: Int,

    @SerialName("credit_id")
    val creditID: String? = null,

    val name: String? = null,
    val gender: Int,

    @SerialName("profile_path")
    val profilePath: String? = null
)
