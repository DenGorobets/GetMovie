package com.den.gorobets.getmovie.ui.elements.shared.image

import android.graphics.Bitmap
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.graphics.Color
import androidx.palette.graphics.Palette

@Composable
fun DominantColorImage(
    inputImage: Bitmap?,
    colorCount: Int = 6,
    outputColors: (List<Color?>, Boolean) -> Unit
) {

    LaunchedEffect(key1 = inputImage) {
        inputImage?.let {
            Palette.from(it).maximumColorCount(colorCount).generate { palette ->

                palette?.dominantSwatch?.let { colors ->
                    outputColors(
                        listOf(
                            Color(colors.rgb),
                            Color(colors.titleTextColor),
                            Color(colors.bodyTextColor)
                        ),
                        true
                    )
                }
            }
        }
    }
}