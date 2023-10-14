package com.den.gorobets.getmovie.dto.description.series

import androidx.annotation.Keep
import kotlinx.serialization.Serializable

@Serializable
@Keep
data class Translations(

    val translations: List<Translation>? = null
)