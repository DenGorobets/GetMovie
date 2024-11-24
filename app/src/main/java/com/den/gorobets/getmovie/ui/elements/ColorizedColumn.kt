package com.den.gorobets.getmovie.ui.elements

import android.graphics.Bitmap
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import com.den.gorobets.getmovie.R
import com.den.gorobets.getmovie.ui.elements.shared.image.DominantColorImage
import com.den.gorobets.getmovie.utils.data.ColorizedTheme

@Composable
fun ColorizedColumn(
    modifier: Modifier = Modifier,
    bitmap: MutableState<Bitmap?>,
    snackbarHostState: SnackbarHostState,
    isColorTheme: Boolean,
    snackbarState: Boolean,
    snackbarPerformed: () -> Unit,
    snackbarDismissed: () -> Unit,
    content: @Composable ColumnScope.(ColorizedTheme) -> Unit
) {

    val context = LocalContext.current

    val colorBackground =
        remember { mutableStateOf<Color?>(null) }
    val colorTextTitle =
        remember { mutableStateOf<Color?>(null) }
    val colorTextBody =
        remember { mutableStateOf<Color?>(null) }

    var isColorChanged by remember { mutableStateOf(false) }
//    val isColorTheme by remember {
//        derivedStateOf { GetMovieApplication.COLORIZED_DESCRIPTION_PAGE }
//    }

//    Log.e("", "isColorTheme $isColorTheme")

    val mainBackColor = MaterialTheme.colorScheme.background
    val mainTextColor = MaterialTheme.colorScheme.onSurface

    val colorizedTitleTextColor: Color by animateColorAsState(
        targetValue = if (isColorTheme && isColorChanged)
            colorTextBody.value ?: mainTextColor
        else
            mainTextColor,
        label = stringResource(R.string.animation_title_text_color_change)
    )

    val colorizedBodyTextColor: Color by animateColorAsState(
        targetValue = if (isColorTheme && isColorChanged)
            colorTextTitle.value ?: mainTextColor.copy(alpha = 0.6f)
        else
            mainTextColor.copy(alpha = 0.6f),
        label = stringResource(R.string.animation_body_text_color_change)
    )

    val colorizedBackColor: Color by animateColorAsState(
        targetValue = if (isColorTheme && isColorChanged)
            colorBackground.value ?: mainBackColor
        else
            mainBackColor,
        label = stringResource(R.string.animation_background_color_change)
    )

    LaunchedEffect(true) {
        if (snackbarState) {
            val result = snackbarHostState
                .showSnackbar(
                    context.getString(R.string.use_colorized_theme),
                    actionLabel = context.getString(R.string.turn_on),
                    withDismissAction = true,
                    duration = SnackbarDuration.Indefinite
                )

            when (result) {
                SnackbarResult.ActionPerformed -> {
                    snackbarPerformed.invoke()
//                    snackbarHostState
//                        .showSnackbar(
//                            context.getString(R.string.colorized_theme_enabled),
//                            duration = SnackbarDuration.Short
//                        )
                }

                SnackbarResult.Dismissed -> {
                    snackbarDismissed.invoke()
                }
            }
        }
    }

    DominantColorImage(
        inputImage = bitmap.value,
        outputColors = { colors, trigger ->
            colorBackground.value = colors[0]
            colorTextTitle.value = colors[1]
            colorTextBody.value = colors[2]
            isColorChanged = trigger
        })

    Column(
        modifier = modifier
            .fillMaxSize()
            .drawBehind {
                drawRect(colorizedBackColor)
            },
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {

        val themeColors = ColorizedTheme(
            colorizedBackColor,
            colorizedTitleTextColor,
            colorizedBodyTextColor
        )

        content(themeColors)
    }
}