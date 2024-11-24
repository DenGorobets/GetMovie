package com.den.gorobets.getmovie.utils.data

import androidx.compose.runtime.Stable

@Stable
data class ReceivedDescriptionData(
    val topPartData: DescriptionTopPartData,
    val bottomPartData: DescriptionBottomPartData
)