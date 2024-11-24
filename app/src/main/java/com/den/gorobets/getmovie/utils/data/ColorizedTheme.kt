package com.den.gorobets.getmovie.utils.data

import androidx.compose.runtime.Stable
import androidx.compose.ui.graphics.Color


@Stable
data class ColorizedTheme(
    val colorizedBackColor: Color,
    val colorizedTitleTextColor: Color,
    val colorizedBodyTextColor: Color
)