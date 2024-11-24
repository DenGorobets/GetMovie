package com.den.gorobets.getmovie.utils.data

import androidx.compose.runtime.Stable

@Stable
data class DescriptionTopPartData(
    val poster: String,
    val backdrop: String,
    val title: String,
    val tagline: String
) {
    val taglineWithQuotes: String = "\"" + tagline + "\""

    fun checkTaglineEmpty(): Boolean = tagline.isNotEmpty()
}