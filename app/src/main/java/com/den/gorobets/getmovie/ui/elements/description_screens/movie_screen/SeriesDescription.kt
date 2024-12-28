package com.den.gorobets.getmovie.ui.elements.description_screens.movie_screen

import android.graphics.Bitmap
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import cafe.adriel.voyager.navigator.Navigator
import com.den.gorobets.getmovie.utils.data.ColorizedTheme
import com.den.gorobets.getmovie.utils.data.ReceivedMovieDescriptionData
import com.den.gorobets.getmovie.utils.data.ReceivedSeriesDescriptionData

@Composable
fun SeriesDescription(
    data: ReceivedSeriesDescriptionData,
    paddingValues: PaddingValues,
    navigator: Navigator,
    themeColors: ColorizedTheme,
    receivedBitmap: MutableState<Bitmap?>
) {

    TopMovieDescriptionItems(
        data = data.topPartData,
        themeBackColor = themeColors.colorizedBackColor,
        themeTextColors = listOf(
            themeColors.colorizedTitleTextColor, themeColors.colorizedBodyTextColor
        ),
        bitmapFromPoster = receivedBitmap
    )
    BottomSeriesDescriptionItems(
        navigator = navigator,
        data = data.bottomPartData,
        themeTextColors = listOf(
            themeColors.colorizedTitleTextColor, themeColors.colorizedBodyTextColor
        )
    )
    Spacer(
        Modifier
            .padding(bottom = paddingValues.calculateBottomPadding())
            .drawBehind {
                drawRect(themeColors.colorizedBackColor)
            })
}
